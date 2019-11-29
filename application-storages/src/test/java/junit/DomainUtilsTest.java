package junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 类名称：DomainUtilsTest<br>
 * 类描述：<br>
 * 创建时间：2019年04月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@RunWith(Parameterized.class)
public class DomainUtilsTest {

    private String domain;
    private boolean expected;

    public DomainUtilsTest(String domain, boolean expected) {
        this.domain = domain;
        this.expected = expected;
    }

    @Parameters(name = "{index}: isValid({0})={1}")
    public static Iterable<Object[]> data(){
        return Arrays.asList(new Object[][]{
                { "google.com", true },
                { "yiibai.com", true },
                { "-yiibai.com", false },
                { "yiibai-.com", false },
                { "3423kjk", false },
                { "mk#$kdo.com", false }
        });
    }

    @Test
    public void testValidDomain(){
        assertEquals(expected,DomainUtils.isValidDomainName(domain));
    }
}
