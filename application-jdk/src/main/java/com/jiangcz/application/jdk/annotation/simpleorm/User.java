package com.jiangcz.application.jdk.annotation.simpleorm;

/**
 * 带注解的实体类,建立了对象和表的映射关系,可以再运行时被解析
 */
@MyTable("t_user")
public class User {

    //主键,对应表字段id,类型为VARCHAR
    @MyColumn(value = "id",constraint = @Constraints(primaryKey = true))
    private String id;

    //对应表字段name,类型为类型为VARCHAR
    @MyColumn(value = "name")
    private String name;

    //对应表字段age,类型为INT,且可为null
    @MyColumn(value = "age",type = "INT",constraint = @Constraints(nullable = true))
    private int age;

    //对应表字段phone_number,类型为VARCHAR,且有唯一约束
    @MyColumn(value = "phone_number",constraint = @Constraints(unique = true))
    private String phoneNumber;
}
