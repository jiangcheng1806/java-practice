package com.jiangcz.application.canal.sync;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.jiangcz.application.common.util.Spring.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * 类名称：JsonUtility<br>
 * 类描述：<br>
 * 创建时间：2019年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@Slf4j
@Component
public class JsonUtility {


    /**
     * 获取json
     *
     * @param beforeColumns 操作前colums
     * @param afterColumns  操作后colums
     * @param eventType     事件类型
     * @return java.lang.String
     * @date 2018/11/15
     * v3.2.5
     */
    public static JSONObject constructCanalJsonObject(List<CanalEntry.Column> beforeColumns, List<CanalEntry.Column> afterColumns, CanalEntry.EventType eventType) {

        JSONObject jsonObject = null;
        if (eventType == CanalEntry.EventType.INSERT) {
            jsonObject = getJson(afterColumns);
        } else if (eventType == CanalEntry.EventType.DELETE) {
            jsonObject = getJson(beforeColumns);
        } else if (eventType == CanalEntry.EventType.UPDATE) {
            jsonObject = getJson(afterColumns);
        }
        return jsonObject;
    }

    /**
     * 拼装json
     *
     * @param columns 列list
     * @return java.lang.String
     * @date 2018/11/15
     * v3.2.5
     */
    public static JSONObject getJson(List<CanalEntry.Column> columns) {
        JSONObject jsonObject = new JSONObject();
        if (CollectionUtils.isNotEmpty(columns)) {
            columns.forEach(column -> {
                if (!column.getIsNull()) {
                    jsonObject.put(column.getName().toLowerCase(), column.getValue());
                }
            });
        }
        return jsonObject;
    }

    /**
     * canal消息体
     *
     * @param entrys  消息体
     * @param batchId id
     * @date 2018/11/14
     * v3.2.5
     */
    @Async
    public void process(List<CanalEntry.Entry> entrys, long batchId) {
        if (CollectionUtils.isEmpty(entrys)) {
            log.warn(" Canal Async method , batchId：{},entrys is null or empty!", batchId);
            return;
        }
        //设置logId
        String logId = UUID.randomUUID().toString().replace("-", "");
        //BaseContextHandler.setLogId(logId);
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }
            CanalEntry.RowChange rowChage;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException(">>>> ERROR ## parser of eromanga-event has an error , data: <<<<<" + entry.toString(), e);
            }
            String tableName = entry.getHeader().getTableName();
            CanalEntry.EventType eventType = rowChage.getEventType();
            log.debug(">>>>batchId：【{}】 tableName,【{}】,eventType：【{}】 <<<<<", batchId, tableName, eventType);
            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                List<CanalEntry.Column> beforeColumns = rowData.getBeforeColumnsList();
                List<CanalEntry.Column> afterColumns = rowData.getAfterColumnsList();
                //mysql数据同步
                JSONObject beforeJson = JsonUtility.getJson(beforeColumns);
                JSONObject afterJson = JsonUtility.getJson(afterColumns);
                log.info("tableName: {} ",tableName);
                log.info("eventType: {} ",eventType);
                log.info("beforeJson: {} ",beforeJson);
                log.info("afterJson: {} ",afterJson);
            }
        }
    }

    /**
     * 获取写操作后的entityJson
     *
     * @param eventType  事件类型
     * @param beforeJson 操作前json
     * @param afterJson  操作后json
     * @return JSONObject
     * 2019/2/27
     * v3.3.5
     */
    public static JSONObject getEntityJson(CanalEntry.EventType eventType, JSONObject beforeJson, JSONObject afterJson) {
        if (null == eventType) {
            return null;
        }
        JSONObject entityJson = null;
        if (CanalEntry.EventType.INSERT == eventType || CanalEntry.EventType.UPDATE == eventType) {
            entityJson = afterJson;
        } else if (CanalEntry.EventType.DELETE == eventType) {
            entityJson = beforeJson;
        }
        return entityJson;
    }

}
