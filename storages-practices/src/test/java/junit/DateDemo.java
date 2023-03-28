package junit;

import java.util.Date;

/**
 * 类名称：DateDemo<br>
 * 类描述：<br>
 * 创建时间：2018年05月10日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class DateDemo {
    public static void main(String[] args) {
        Date date = new Date(98,5,21);
        Date date1 = new Date(99,1,9);

        int comparison = date.compareTo(date1);
        int comparison1 = date1.compareTo(date);
        int comparison2 = date.compareTo(date);

        // print the results
        System.out.println("Comparison Result:" + comparison);
        System.out.println("Comparison2 Result:" + comparison1);
        System.out.println("Comparison3 Result:" + comparison2);
    }
}
