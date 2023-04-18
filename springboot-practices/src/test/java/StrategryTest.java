import com.jiangc.strategy.autoregister.AutoRegisterChargeStrategyFactory;

import com.jiangc.strategy.autoregister.ChargeStrategy2;
import com.jiangc.strategy.base.ChargeStrategryFactory;
import com.jiangc.strategy.base.ChargeStrategy;
import com.jiangc.strategy.base.ChargeType;
import com.jiangc.strategy.strategymap.MapChargeStrategyFactory;
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

    @Test
    public void test2(){
        try {
            ChargeStrategy chargeStrategy = MapChargeStrategyFactory.getChargeStrategry(ChargeType.INTERNAL);
            double charge = chargeStrategy.charge(100);
            System.out.println("内部价格："+charge);

            chargeStrategy = MapChargeStrategyFactory.getChargeStrategry(ChargeType.EXTERNAL);
            charge = chargeStrategy.charge(100);
            System.out.println("外部价格："+charge);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test3(){
        try {
            ChargeStrategy2 chargeStrategy = AutoRegisterChargeStrategyFactory.getChargeStrategy(ChargeType.INTERNAL);
            double charge = chargeStrategy.charge(100);
            System.out.println("内部价格："+charge);

            chargeStrategy = AutoRegisterChargeStrategyFactory.getChargeStrategy(ChargeType.EXTERNAL);
            charge = chargeStrategy.charge(100);
            System.out.println("外部价格："+charge);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
