package junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

/**
 * 类名称：FibonacciTest<br>
 * 类描述：使用 Parameter 注解 需要 属性为public 的 并且 去掉多参构造函数<br>
 * 创建时间：2019年04月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@RunWith(Parameterized.class)
public class ParametersTest2 {

    @Parameterized.Parameters
    public static Object[] data(){
        return new Object[]{"first test","second test"};
    }

    @Parameterized.Parameter
    public /*private*/ String fInput;

    @Test
    public void test(){
        assertEquals("first test", fInput);
    }

}
