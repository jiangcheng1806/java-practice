package junit;

import java.util.regex.Pattern;

/**
 * 类名称：DomainUtils<br>
 * 类描述：<br>
 * 创建时间：2019年04月19日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class DomainUtils {
    private static Pattern pDomainName;

    private static final String DOMAIN_NAME_PATTERN = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

    static {
        pDomainName = Pattern.compile(DOMAIN_NAME_PATTERN);
    }

    public static boolean isValidDomainName(String domainName) {
        return pDomainName.matcher(domainName).find();
    }

}
