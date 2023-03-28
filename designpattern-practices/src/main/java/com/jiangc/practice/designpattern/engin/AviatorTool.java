package com.jiangc.practice.designpattern.engin;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

public class AviatorTool {

    int i;
    float f;
    Date date;

    // 构造方法
    public AviatorTool(int i, float f, Date date) {
        this.i = i;
        this.f = f;
        this.date = date;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Long execute = (Long) AviatorEvaluator.execute("1+3+4");
        System.out.println(execute);

        System.out.println(AviatorEvaluator.exec("a>1?'yes':'no'", 2));
        System.out.println(AviatorEvaluator.exec("a>1?a * 2:a - 1", 2));

        String email = "sssdaddadads02@gmail.com";
        Map<String,Object> env = new HashMap<>();
        env.put("email",email);
        String username = (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env);
        System.out.println(username); // killme2008

        String expression = "a - b + c * d";
        Expression compile = AviatorEvaluator.compile(expression);
        Map<String, Object> env1 = new HashMap<String, Object>();
        env1.put("a", 1);
        env1.put("b", 2);
        env1.put("c", 3);
        env1.put("d", 4);
        Long execute1 = (Long) compile.execute(env1);
        System.out.println(execute1);



        final List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add(" world");
        final int[] array = new int[3];
        array[0] = 0;
        array[1] = 1;
        array[2] = 3;
        final Map<String, Date> map = new HashMap<String, Date>();
        map.put("date", new Date());
        Map<String, Object> env2 = new HashMap<String, Object>();
        env2.put("list", list);
        env2.put("array", array);
        env2.put("mmap", map);
        System.out.println(AviatorEvaluator.execute("list[0]+list[1]", env2));   // hello world
        System.out.println(AviatorEvaluator.execute("'array[0]+array[1]+array[2]=' + (array[0]+array[1]+array[2])", env2));  // array[0]+array[1]+array[2]=4
        System.out.println(AviatorEvaluator.execute("'today is ' + mmap.date ", env2));  // today is Wed Feb 24 17:31:45 CST 2016


        AviatorTool foo = new AviatorTool(100, 3.14f, new Date());
        Map<String, Object> env3 = new HashMap<String, Object>();
        env3.put("foo", foo);
        System.out.println(AviatorEvaluator.execute("'foo.i = '+foo.i", env3));   // foo.i = 100
        System.out.println(AviatorEvaluator.execute("'foo.f = '+foo.f", env3));   // foo.f = 3.14
        System.out.println(AviatorEvaluator.execute("'foo.date.year = '+(foo.date.year+1990)", env3));  // foo.date.year = 2106


        Map<String, Object> env4 = new HashMap<String, Object>();
        final Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(date);
        env4.put("date", date);
        env4.put("dateStr", dateStr);
        Boolean result = (Boolean) AviatorEvaluator.execute("date==dateStr", env4);
        System.out.println(result);  // true
        result = (Boolean) AviatorEvaluator.execute("date > '2010-12-20 00:00:00:00' ", env4);
        System.out.println(result);  // true
        result = (Boolean) AviatorEvaluator.execute("date < '2200-12-20 00:00:00:00' ", env4);
        System.out.println(result);  // true
        result = (Boolean) AviatorEvaluator.execute("date==date ", env4);
        System.out.println(result);  // true

        System.out.println(AviatorEvaluator.exec("99999999999999999999999999999999 + 99999999999999999999999999999999"));

        Object rt = AviatorEvaluator.exec("9223372036854775807100.356M * 2");
        System.out.println(rt + " " + rt.getClass());  // 18446744073709551614200.712 class java.math.BigDecimal
        rt = AviatorEvaluator.exec("92233720368547758074+1000");
        System.out.println(rt + " " + rt.getClass());  // 92233720368547759074 class java.math.BigInteger
        BigInteger a = new BigInteger(String.valueOf(Long.MAX_VALUE) + String.valueOf(Long.MAX_VALUE));
        BigDecimal b = new BigDecimal("3.2");
        BigDecimal c = new BigDecimal("9999.99999");
        rt = AviatorEvaluator.exec("a+10000000000000000000", a);
        System.out.println(rt + " " + rt.getClass());  // 92233720368547758089223372036854775807 class java.math.BigInteger
        rt = AviatorEvaluator.exec("b+c*2", b, c);
        System.out.println(rt + " " + rt.getClass());  // 20003.19998 class java.math.BigDecimal
        rt = AviatorEvaluator.exec("a*b/c", a, b, c);
        System.out.println(rt + " " + rt.getClass());  // 2.951479054745007313280155218459508E+34 class java.math.BigDecimal

        Map<String, Object> env5 = new HashMap<String, Object>();
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        list1.add(3);
        list1.add(20);
        list1.add(10);
        env5.put("list", list1);
        Object result1 = AviatorEvaluator.execute("count(list)", env5);
        System.out.println(result1);  // 3
        result1 = AviatorEvaluator.execute("reduce(list,+,0)", env5);
        System.out.println(result1);  // 33
        result1 = AviatorEvaluator.execute("filter(list,seq.gt(9))", env5);
        System.out.println(result1);  // [10, 20]
        result1 = AviatorEvaluator.execute("include(list,10)", env5);
        System.out.println(result1);  // true
        result1 = AviatorEvaluator.execute("sort(list)", env5);
        System.out.println(result1);  // [3, 10, 20]
        AviatorEvaluator.execute("map(list,println)", env5);

        AviatorEvaluator.setOption(Options.TRACE_EVAL,true);
        AviatorEvaluator.setTraceOutputStream(new FileOutputStream(new File("aviator.log")));


        // 注册函数
        AviatorEvaluator.addFunction(new AddFunction());
        System.out.println(AviatorEvaluator.execute("add(1, 2)"));
        System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)"));
    }
}

/**
 * 自定义函数
 */
class AddFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Number number1 = FunctionUtils.getNumberValue(arg1, env);
        Number number2 = FunctionUtils.getNumberValue(arg2, env);
        return new AviatorDouble(number1.doubleValue() + number2.doubleValue());
    }

    public String getName() {
        return "add";
    }
}
