package junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 类名称：Application<br>
 * 类描述：<br>
 * 创建时间：2018年05月10日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */


//1.需要Parameterized运行器
@RunWith(Parameterized.class)
public class ParameterTest {

    //2.声明变量存放预期值和测试数据
    private String firstName;
    private String lastName;


    //3.声明一个返回值为Collection的公共静态方法，并使用@Parameters进行修饰
    @Parameterized.Parameters
    public static List<Object[]> param(){
        // 两个测试用例
        return Arrays.asList(new Object[][]{{"Sid","Lee"},{"Tom","Cat"}});
    }


    //4.为测试类声明一个带有参数的公共构造函数，并在其中为之声明变量赋值
    public ParameterTest(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //5.进行测试,发现它会将所有的测试用例测试一遍
    @Test
    public void test(){
        String name = firstName + " " + lastName;
        assertThat("Sid Lee",is(name));
    }

}
