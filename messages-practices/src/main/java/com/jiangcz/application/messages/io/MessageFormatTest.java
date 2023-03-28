package com.jiangcz.application.messages.io;

import java.text.MessageFormat;
import java.util.Date;

/**
 * 类名称：MessageFormatTest<br>
 * 类描述：<br>
 * 创建时间：2018年05月23日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class MessageFormatTest {
    public static void main(String[] args) {
        String text = "this is {0}";
        String param = "666";
        String result = MessageFormat.format(text,param);
        System.out.println(result);
    }

    public void testMessageFormat(){
        /**这是源码注释中的一个例子
         * 在这个例子中静态方法format第一个参数是字符串类型的，
         * 即模式字符串，第二个参数是个可变参数，实际上就是一个Object类型的数组。
         * 在模式字符串中使用"{}"标识一个FormatElement。"{}"中的ArgumentIndex对应Object数组中响应索引处的值。
         */
        int planet = 7;
        String event = "a disturbance in the Force";
        String result = MessageFormat.format("At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
                planet, new Date(), event);
        System.out.println(result);
//输出：At 20:39:15 on 2015-12-11, there was a disturbance in the Force on planet 7.
    }
}
