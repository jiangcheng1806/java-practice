package com.jiangcz.application.jdk.annotation.simpleorm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 运行时注解解析器
 */
public class TableGenerator {
    /**
     * 运行时解析注解生成对应的建表语句
     *
     * @param clazz 与表对应的实体的Class对象
     * @return
     */
    public static String generateSql(Class clazz){
        String table; // 表名
        List<String> columnSegments = new ArrayList<String>();
        //获取表注解
        MyTable myTable = (MyTable) clazz.getAnnotation(MyTable.class);
        if (myTable == null) {
            throw new IllegalArgumentException("表注解不能为空!");
        }
        //获取表名
        table = myTable.value();
        //获取所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            MyColumn column = field.getAnnotation(MyColumn.class);
            if (column == null) {
                continue;//为null说明该字段不为映射字段,也就是没有加上字段注解
            }
            StringBuilder columnSegement = new StringBuilder();//字段分片,eg:"id varchar(50) primary key"
            String columnType = column.type().toUpperCase();//字段类型
            String columnName = column.value().toUpperCase();//字段名
            columnSegement.append(columnName).append(" ").append(columnType).append(" ");
            Constraints constraints = column.constraint();
            boolean primaryKey = constraints.primaryKey();
            boolean nullable = constraints.nullable();
            boolean unique = constraints.unique();
            if (primaryKey) {
                //主键唯一且不为空
                columnSegement.append("PRIMARY KEY ");
            } else if (!nullable) {
                //字段不为null
                columnSegement.append("NOT NULL ");
            }
            if (unique) {
                //有唯一键
                columnSegement.append("UNIQUE ");
            }
            columnSegments.add(columnSegement.toString());

        }

        if (columnSegments.size() < 1) {
            //没有映射任何表字段,抛出异常
            throw new IllegalArgumentException("没有映射任何表字段!");
        }
        StringJoiner joiner = new StringJoiner(",", "(", ")");
        for (String segement : columnSegments) {
            joiner.add(segement);
        }
        //生成SQL语句
        return String.format("CREATE TABLE %s", table) + joiner.toString();

    }
}
