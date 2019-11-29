package junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * 类名称：FibonacciTest<br>
 * 类描述：<br>
 * 创建时间：2019年04月18日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@RunWith(Parameterized.class)
public class FibonacciTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}, {5, 5}, {6, 8}
        });
    }

    private int fInput;
    private int fExpected;

    public FibonacciTest(int fInput, int fExpected) {
        this.fInput = fInput;
        this.fExpected = fExpected;
    }

    @Test
    public void test(){
        assertEquals(fExpected,Fibonacci.compute(fInput));
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
