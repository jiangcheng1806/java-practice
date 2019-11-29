package com.jiangcz.application.jdk.annotation.exec;

import java.lang.reflect.Method;

@MyAnnotation(lamp = EnumTest.TrafficLamp.YELLOW, value = "startup-jdk", clazz = AnnotationExec.class, annotation = @MetaAnnotation("startup-jdk1"))
public class AnnotationExec {


    @SuppressWarnings("deprecation")
    @MyAnnotation("method")//自定义注解应用在方法上
    public static void main(String[] args) throws NoSuchMethodException, SecurityException {

        System.runFinalizersOnExit(true);//这是一个过时了的方法 ,如果没有注解就会有警告提示

        //判断此类是否有MyAnnotation注解
        if (AnnotationExec.class.isAnnotationPresent(MyAnnotation.class)) {
            //如果有，则获取该注解
            MyAnnotation annotation = AnnotationExec.class.getAnnotation(MyAnnotation.class);
			
			
			/*@com.chanspace.MyAnnotation(annotation=@com.chanspace.MetaAnnotation(value=startup-jdk1), arr=[1, 2, 3], lamp=YELLOW, color=red, clazz=class com.chanspace.AnnotationExec, value=startup-jdk)
			red
			startup-jdk
			3
			YELLOW
			startup-jdk1
			class com.chanspace.AnnotationExec
			*/
            System.out.println(annotation);
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            System.out.println(annotation.arr().length);
            System.out.println(annotation.lamp());
            System.out.println(annotation.annotation().value());
            System.out.println(annotation.clazz());
        }


        //获取方法上的注解
        Method main = AnnotationExec.class.getMethod("main", String[].class);
        MyAnnotation annotation = main.getAnnotation(MyAnnotation.class);
        SuppressWarnings sw = main.getAnnotation(SuppressWarnings.class);
		
		/*null
		@com.chanspace.MyAnnotation(annotation=@com.chanspace.MetaAnnotation(value=startup-jdk), arr=[1, 2, 3], lamp=GREEN, color=red, clazz=class java.lang.System, value=method)
		*/
        System.out.println(sw);
        System.out.println(annotation);
        System.out.println("Hello World!");
    }
}
