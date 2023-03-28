package junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

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
public class FibonacciTest1 {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}, {5, 5}, {6, 8}
        });
    }

    @Parameterized.Parameter
    public /*private*/ int fInput;
    @Parameterized.Parameter(1)
    public /*private*/ int fExpected;

    /*public FibonacciTest1(int fInput, int fExpected) {
        this.fInput = fInput;
        this.fExpected = fExpected;
    }*/

    @Test
    public void test(){
        assertEquals(fExpected, Fibonacci.compute(fInput));
    }

    private static class Fibonacci {
        public static int compute(int n) {
            int result = 0;

            if (n <= 1){
                result = n;
            } else {
                result = compute(n - 1) + compute(n - 2);
            }

            return result;
        }
    }
}
