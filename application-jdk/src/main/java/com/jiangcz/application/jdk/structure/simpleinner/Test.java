package com.jiangcz.application.jdk.structure.simpleinner;

import com.jiangcz.application.jdk.structure.simpleinner.WithInner.Inner;

/**
 * 类名称：Test<br>
 * 类描述：<br>
 * 创建时间：2018年08月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class Test extends Inner {
    Test(WithInner wi){
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        Test test = new Test(wi);
    }
}
