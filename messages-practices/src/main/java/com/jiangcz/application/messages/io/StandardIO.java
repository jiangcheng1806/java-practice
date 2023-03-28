package com.jiangcz.application.messages.io;

import java.util.Scanner;

/**
 * 类名称：StandardIO<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class StandardIO {

    /**
     * 这里的输入是类型比较复杂的，如果只是单一类型的使用相应的hasNextXXX()，作为循环判断条件就可以了。 一般情况都会使用hasNext()或者hasNextLine()但其实这两个方法是阻塞式的会一直等待你的输入，但是尝尝需要直接输入回车的时候就停止输入，就无法满足了
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            String str = scanner.nextLine();
            if (str.equals("")){
                break;
            } else {
                System.out.println(str);
            }
        }
    }
}
