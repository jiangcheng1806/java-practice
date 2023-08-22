package com.jiangcz.application.jdk.reflect.construct;

import com.jiangcz.application.common.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * 构造方法测试
 */
public class ConstructTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Logger logger = LoggerFactory.getLogger(ConstructTest.class);

        //获取class对象
        Class<A> aClass = A.class;

        //获取构造方法
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            logger.debug("构造方法 {}", constructor);
        }

        // 指定参数的构造方法
// getConstructor方法传入的参数为Class类型的对象,代表的是参数类型
// 想获得上面的带一个String参数的构造方法,就需要传入String的Class对象
        Constructor<A> constructor = aClass.getConstructor(String.class);
        logger.debug("指定参数的构造方法 {}", constructor);

        // 构造方法参数
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            logger.debug("构造方法参数 {}", parameterType);
        }

        // 利用Constructor对象实例化一个类
// 刚才我们获取的是只带一个String参数的构造方法,所以实例化对象,只需传入一个String参数即可
        A a = constructor.newInstance("小明");
        logger.debug("{}", JacksonUtils.toJSONString(a));


        // 获取全限定类名（包含包名）
        String name = aClass.getName();
        logger.debug("全限定类名 {}", name);

// 获取类的名字(不包含包名)
        String simpleName = aClass.getSimpleName();
        logger.debug("类的名字(不包含包名) {}", simpleName);


// 获取当前类或者接口的全部修饰符比如public, protected, private, final, static, abstract and interface等等
// ，但是返回的是一个编码之后的int整数
        int modifiers = aClass.getModifiers();
        logger.debug("修饰符 {}", modifiers);

// 可以使用Modifier类，来判断当前修饰符是否包含指定的修饰符
        logger.debug("是否包含抽象修饰符 {}", Modifier.isAbstract(modifiers));
        logger.debug("是否包含私有修饰符 {}", Modifier.isPrivate(modifiers));

// 获取包名
        Package aPackage = aClass.getPackage();
        logger.debug("包名 {}", aPackage.getName());

// 获取父类
        Class<? super A> superclass = aClass.getSuperclass();
        logger.debug("父类 {}", superclass);

// 获取实现的接口
// 注意：getInterfaces()方法仅仅只返回当前类所实现的接口。当前类的父类如果实现了接口，这些接口是不会在返回的Class集合中的，尽管实际上当前类其实已经实现了父类接口。
        Class<?>[] interfaces = aClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            logger.debug("接口 {}", anInterface);
        }

// 获取方法，包括父类的（只能获取public的）
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            logger.debug("public方法 {}", method);
        }

// 获取成员变量（只能获取public的）
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            logger.debug("public成员变量 {}", field);
        }

// 获取注解
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            logger.debug("注解 {}", annotation);
        }
    }
}
