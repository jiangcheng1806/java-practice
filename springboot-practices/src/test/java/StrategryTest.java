import com.jiangc.strategy.ChargeStrategryFactory;
import com.jiangc.strategy.ChargeStrategy;
import com.jiangc.strategy.ChargeType;
import org.junit.Test;

public class StrategryTest {

    @Test
    public void test1(){
        try {
            ChargeStrategy chargeStrategy = ChargeStrategryFactory.getChargeStrategy(ChargeType.INTERNAL);
            double charge = chargeStrategy.charge(100);
            System.out.println("内部价格："+charge);

            chargeStrategy = ChargeStrategryFactory.getChargeStrategy(ChargeType.EXTERNAL);
            charge = chargeStrategy.charge(100);
            System.out.println("外部价格："+charge);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
