package com.jiangcz.application.common.util.Spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 类名称：SpringContextUtil<br>
 * 类描述：<br>
 * 创建时间：2019年08月27日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static String getActiveProfile() {
        String activeProfile = "";
        if(null!=context && null!= context.getEnvironment() && null!=context.getEnvironment().getActiveProfiles()){
            activeProfile = context.getEnvironment().getActiveProfiles()[0];
        }
        //金丝雀环境和prod环境共用
        if(null!=activeProfile &&"canary".equals(activeProfile)){activeProfile = "prod";}
        return activeProfile;
    }


    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
