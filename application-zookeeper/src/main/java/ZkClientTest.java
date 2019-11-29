/**
 * Project Name:mk-project <br>
 * Package Name:com.suns.zookeeper.zkclient <br>
 *
 * @author mk <br>
 * Date:2018-10-31 11:05 <br>
 */


import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;


/**
 * zkclient客户端使用
 * 和原生zookeeper优点：
 * 1.使用api更方便
 * 2.订阅节点数据改变或者子节点变化，只需要订阅一次，便可以一直使用。而原生zookeeper的监听是一次性的，需要重复注册。
 * ClassName: ZkClientTest <br>
 * Description:  <br>
 * @author mk
 * @Date 2018-10-31 11:05 <br>
 * @version
 */
public class ZkClientTest {

    public static final String connect = "127.0.0.1:2181";
    private static ZkClient zkClient = null;
    private static String nodePath = "/zkclient1";
    private static String nodeChildPath = "/zkclient1/n1/n11/n111/n1111";

    public static void main(String[] args) throws Exception {

        //初始化
        init(connect,5000);

        //订阅节点数据改变或者子节点变化，只需要订阅一次，便可以一直使用。而原生zookeeper的监听是一次性的，需要重复注册。
        subscribe();

        //新增
        create(nodePath,"n1");
        //递归新增
        createRecursion(nodeChildPath,"n1");

        //查询
        query(nodePath);

        //修改
        update(nodePath,"n11");

        //单个节点删除
//        delete(nodePath);
        //递归删除
        deleteRecursion(nodePath);

    }

    private static void deleteRecursion(String path) {
        boolean result = zkClient.deleteRecursive(path);
        System.out.println("delete:"+"["+path+"],result:"+result);
    }

    private static void delete(String path) {
        boolean result = zkClient.delete(path);
        System.out.println("delete:"+"["+path+"],result:"+result);
    }

    private static void update(String path, String data) {
        Stat stat = zkClient.writeData(path, data);
        System.out.println("setData:"+"["+path+"],stat:"+stat);
    }

    private static void query(String path) {
        Object o = zkClient.readData(path);
        System.out.println("query:"+"["+path+"],result:"+o);
    }

    private static void createRecursion(String path,String data)  {
        zkClient.createPersistent(path,true);
        System.out.println("create:"+"["+path+"-->"+data);
    }

    private static void create(String path, String data) {
        boolean exists = zkClient.exists(path);
        if(exists){
            System.out.println("节点["+path+"]已存在，不能新增");
            return;
        }
        String result = zkClient.create(path, data, CreateMode.PERSISTENT);
        System.out.println("create:"+"["+path+"-->"+data+"],result:"+result);
    }

    private static void subscribe() {
        //订阅节点内容改变
        zkClient.subscribeDataChanges(nodePath, new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                System.out.println("handleDataChange----->"+path+"|"+data);
            }

            @Override
            public void handleDataDeleted(String path) throws Exception {
                System.out.println("handleDataDeleted----->"+path);
            }
        });

        //订阅子节点改变
        zkClient.subscribeChildChanges(nodePath, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> list) throws Exception {
                System.out.println("handleChildChange----->"+parentPath+"|"+list);
            }
        });

    }

    private static void init(String connect, int sessionTimeout) {
        zkClient = new ZkClient(connect, sessionTimeout);
    }
}