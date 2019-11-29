package com.jiangcz.application.jdk.reflect.test1;

import com.jiangcz.application.jdk.annotation.timer.Timer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 类名称：TimeUtil<br>
 * 类描述：<br>
 * 创建时间：2018年08月25日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class TimeUtil {
    public void getTime(CallBack callBack){
        long start = System.currentTimeMillis();
        callBack.execute();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void getTime() {
        //获取当前类型名字
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        System.out.println("current className(expected):" + className);
        try {
            Class c = Class.forName(className);
            Object obj = c.newInstance();
            Method[] methods = c.getDeclaredMethods();
            for(Method m : methods){
                // 判断该方法是否包含Timer注解
                if (m.isAnnotationPresent(Timer.class)){
                    m.setAccessible(true);
                    long start = System.currentTimeMillis();
                    //执行该方法
                    m.invoke(obj);
                    long end = System.currentTimeMillis();
                    System.out.println(m.getName() + "() time consumed: " + String.valueOf(end - start) + "\\\\n");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //修改getTime
    public HashMap<String,Long> getMethodsTable(){
        HashMap<String,Long> methodsTable = new HashMap();
        String className = Thread.currentThread().getStackTrace()[3].getClassName();
        //...
        return methodsTable;
    }

    public void printChart(){
        Map<String,Long> result = sortByValue(getMethodsTable());
        double max = result.values().iterator().next();
        for(Map.Entry<String,Long> e:result.entrySet()) {
            double index = e.getValue() / max * 100;
            for (int i = 0; i < index; i++) {
                System.out.println("=");
            }
            System.out.println(e.getKey() + "()" + " Index: " + (long)index + " Time:" + e.getValue());
        }
    }

    /**
     * 该方法为map按value倒排一般方法
     * @param unsortedMap
     * @return
     */
    public LinkedHashMap<String,Long> sortByValue(Map<String,Long> unsortedMap){
        List<Map.Entry<String,Long>> list = new LinkedList(unsortedMap.entrySet());
        //Collections.sort(list, Comparator.comparing(Map.Entry::getValue));//从大到小
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        LinkedHashMap<String,Long> sortedMap = new LinkedHashMap<>();
        Iterator<Map.Entry<String,Long>> iterator = list.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Long> entry = iterator.next();
            sortedMap.put(entry.getKey(),entry.getValue());
        }
        return sortedMap;
    }
}
