package com.jiangcz.application.canal.sync;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import com.jiangcz.application.common.util.Spring.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

/**
 * 类名称：CanalClient<br>
 * 类描述：<br>
 * 创建时间：2019年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@Service
@Slf4j
public class CanalClient {

    /**
     * 单机模式
     */
    private static final String ACTIVE_SINGLE = "single";
    /**
     * 双机模式+zookeeper高可用模式
     */
    private static final String ACTIVE_HA = "ha";

    /**
     * 异常等待时间
     */
    private static final long EXCEPTION_SECONDS = 10;
    /**
     * 线程最长休眠时间
     */
    private static final long MAX_SLEEP_SECONDS = 30;

    @Value("${canal.server.url}")
    private String url;

    @Value("${canal.server.port}")
    private int port;

    @Value("${canal.server.username}")
    private String username;

    @Value("${canal.server.password}")
    private String password;

    @Value("${canal.server.zkServers}")
    private String zkServers;

    @Value("${canal.server.subscribe}")
    private String subscribe;

    @Value("${canal.server.destination}")
    private String destination;

    @Value("${canal.server.refreshSeconds}")
    private long refreshSeconds;

    @Value("${canal.server.dbname}")
    private String dbname;

    private final JsonUtility jsonUtility;

    private final Environment environment;

    @Autowired
    public CanalClient(JsonUtility jsonUtility, Environment environment) {
        this.jsonUtility = jsonUtility;
        this.environment = environment;
    }

    private CanalConnector connector;

    /**
     * 创建连接
     *
     * @date 2018/11/14
     * v3.2.5
     */
    private void run() {
        String activeType = getActiveType();
        if (null == activeType) {
            log.error(">>>> Connection canal server failed,activeType is null <<<<<");
            return;
        }
        //去除空格
        subscribe = subscribe.replaceAll(" ", "");
        String joinStr = dbname + ".";
        subscribe = joinStr + subscribe.replaceAll(",", "," + joinStr);
        int batchSize = 1000;
        //单机/高可用连接地址,用于日志打印
        String serverAddr = ACTIVE_SINGLE.equals(activeType) ? url : zkServers;
        try {
            while (true) {
                //初始化连接,或连接失效时，连接canal server,每次获取数据时都检查,确保连接有效性
                if (null == connector || !connector.checkValid()) {
                    try {
                        if (ACTIVE_SINGLE.equals(activeType)) {
                            //单机
                            connector = CanalConnectors.newSingleConnector(new InetSocketAddress(url, port), destination, username, password);
                        } else {
                            //高可用
                            connector = CanalConnectors.newClusterConnector(zkServers, destination, "", "");
                        }
                        log.debug(">>>> Connection canal server successful,url：【{}】,port：【{}】,subscribe：【{}】 <<<<<", serverAddr, port, subscribe);
                        connector.connect();
                        connector.subscribe(subscribe);
                        log.debug(">>>> Connection canal server successful,subscribe：【{}】 <<<<<", subscribe);
                    } catch (Exception e) {
                        log.debug(">>>> Connection canal server failed,activeType：【{}】,subscribe：【{%s}】, exception：【{%s}】...try again after 10s <<<<<", activeType, subscribe, e.getMessage());
                        Thread.sleep(EXCEPTION_SECONDS);
                        continue;
                    }
                }
                // 获取指定数量的数据
                Message message = connector.getWithoutAck(batchSize);
                if (null == message) {
                    log.debug(">>>> Canal client connect activeType：{} server is running, get Message is null! <<<<<", activeType);
                    Thread.sleep(EXCEPTION_SECONDS);
                    continue;
                }
                //刷新间隔时间不超过30s
                refreshSeconds = refreshSeconds > MAX_SLEEP_SECONDS ? EXCEPTION_SECONDS : refreshSeconds;
                //获取同步id
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    Thread.sleep(refreshSeconds * 1000);
                    refreshSeconds++;
                    if (refreshSeconds < MAX_SLEEP_SECONDS) {
                        log.debug(">>>> Canal client connect canal server is running, get Message size is empty, ...try again after {}s! <<<<<", refreshSeconds);
                    } else if (refreshSeconds == MAX_SLEEP_SECONDS) {
                        //每隔30S,打印一次监听数据表的信息
                        log.debug(">>>> Canal client connect canal server【{}】,subscribe：【{}】is running, get Message size is empty, ...try again after {}s! <<<<<", serverAddr, subscribe, refreshSeconds);
                    }
                } else {
                    try {
                        // 异步请求开始时间
                        long asyncBeginTime = System.currentTimeMillis();
                        jsonUtility.process(message.getEntries(), batchId);
                        //结束时间
                        long asyncEndTime = System.currentTimeMillis();
                        log.debug(">>>>Canal clent batchId：{},Async method time-consuming：{}ms<<<<<", batchId, asyncEndTime - asyncBeginTime);
                    } catch (Exception e) {
                        log.error(">>>> PrintEntry Exception：{} <<<<<", e);
                    }
                }
                // 提交确认
                connector.ack(batchId);
            }
        } catch (Throwable e) {
            log.error(">>>> get message from canal canal server：【{}】,dbname【{}】,error：【{}】 <<<<<", serverAddr, dbname, e.getMessage());
            //断开连接,初始化连接参数,由定时任务检查重连canal server端
            connector.disconnect();
            connector = null;
            subscribe = environment.getProperty("canal.server.subscribe");
        }
    }

    /**
     * 获取Canal连接类型
     * 生产、金丝雀、准生产环境为高可用模式
     * 其它环境为单机模式
     *
     * @return String
     * 2019/11/14
     * v3.2.5
     */
    private String getActiveType() {
        String activeProfile = SpringContextUtil.getActiveProfile();
        String flag;
        switch (activeProfile) {
            case "local":
                flag = ACTIVE_SINGLE;
                break;
            case "dev":
                flag = ACTIVE_SINGLE;
                break;
            case "test":
                flag = ACTIVE_SINGLE;
                break;
            case "forecast":
                flag = ACTIVE_HA;
                break;
            case "prodbeta":
                flag = ACTIVE_SINGLE;
                break;
            case "canary":
                flag = ACTIVE_HA;
                break;
            case "prod":
                flag = ACTIVE_HA;
                break;
            default:
                flag = ACTIVE_SINGLE;
        }
        return flag;
    }

    /**
     * 定时任务检查canal连接的有效性
     *
     * 2019/3/10
     * v3.3.5
     */
    @Scheduled(cron = "0/60 * * * * ?")
    public void checkValid() {
        log.debug(">>>> check available connect <<<<<");
        if (null == connector || !connector.checkValid()) {
            log.debug(">>>> no available connect , try connect again! <<<<<");
            run();
        }
    }

}
