package com.jiangcz.application.jdk.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 测试jdk的内省操作
 * <p>
 * 在类中用私有字段来储存，如果读取或设置这些字段的值，则需要通过一些相应的方法来访问。
 *
 * @author jiangcheng
 */
public class IntrospectorExec {

    public static void main(String[] args) throws Exception {
        App app = new App("hank", "1001");
        String property = "roofsName";


        System.out.println("使用内省简单方式：");
        //用内省的简单方式
        Object retval = getProperty(app, property);
        System.out.println("用内省的方式获取值roofsName：" + retval);


        //使用内省的复杂方式
        System.out.println("使用内省较复杂方式：");
        Object retval1 = getPropertyComplex(app, property);
        System.out.println("用内省的方式获取值roofsName：" + retval1);

        //使用BeanUtils工具包的方式
        System.out.println("使用BeanUtil方式：");
        Object retval2 = BeanUtils.getProperty(app, property);
        System.out.println("使用BeanUtils工具包的方式获取roofName:" + retval2);

        Object value = "lork";
        setProperty(app, property, value);

        System.out.println("用内省的方式获赋值之后roofsName：" + app.getRoofsName());


        //使用BeanUtil的强大操作
        BeanUtils.setProperty(app, property, "59");
        System.out.println(BeanUtils.getProperty(app, property));
        System.out.println(BeanUtils.getProperty(app, property).getClass().getName());


        BeanUtils.setProperty(app, property, true);
        System.out.println(BeanUtils.getProperty(app, property));
        System.out.println(BeanUtils.getProperty(app, property).getClass().getName());
    }

    /**
     * 获取并调用set方法
     *
     * @param app
     * @param propertyName
     * @param value
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void setProperty(App app, String propertyName, Object value) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, app.getClass());
        Method methodSet = pd.getWriteMethod();
        methodSet.invoke(app, value);
    }


    /**
     * 获取并get方法
     *
     * @param app
     * @param propertyName
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object getProperty(App app, String propertyName) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, app.getClass());
        Method methodGet = pd.getReadMethod();
        Object retval = methodGet.invoke(app);
        return retval;
    }


    /**
     * 第二种较复杂的获取并调用JavaBean中的getX方法
     *
     * @param app
     * @param propertyName
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object getPropertyComplex(App app, String propertyName) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(app.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        Object retval = null;

        for (PropertyDescriptor pd : pds) {
            //如果属性跟参数的属性相等，就获取它的getX方法
            if (pd.getName().equals(propertyName)) {
                Method methodGet = pd.getReadMethod();//获取get方法
                retval = methodGet.invoke(app);
                break;
            }
        }

        return retval;

    }
}
