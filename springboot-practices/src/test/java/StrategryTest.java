import com.jiangc.strategy.annotation_4.AnnotationChargeStrategyFactory;
import com.jiangc.strategy.annotation_4.ChargeStrategy3;
import com.jiangc.strategy.autoregister_3.AutoRegisterChargeStrategyFactory;

import com.jiangc.strategy.autoregister_3.ChargeStrategy2;
import com.jiangc.strategy.base_1.ChargeStrategryFactory;
import com.jiangc.strategy.base_1.ChargeStrategy;
import com.jiangc.strategy.base_1.ChargeType;
import com.jiangc.strategy.spring_5.AnnotationChargeStrategyFactory2;
import com.jiangc.strategy.spring_5.ChargeStrategy4;
import com.jiangc.strategy.strategymap_2.MapChargeStrategyFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import test.ExcelApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExcelApplication.class)
@WebAppConfiguration
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

    @Test
    public void test4(){
        try {
            ChargeStrategy3 chargeStrategy3 = AnnotationChargeStrategyFactory.getChargeStrategy(ChargeType.INTERNAL);
            double charge = chargeStrategy3.charge(100);
            System.out.println("内部价格："+charge);

            chargeStrategy3 = AnnotationChargeStrategyFactory.getChargeStrategy(ChargeType.EXTERNAL);
            charge = chargeStrategy3.charge(100);
            System.out.println("外部价格："+charge);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test5(){
        try {
            ChargeStrategy4 chargeStrategy3 = AnnotationChargeStrategyFactory2.getChargeStrategy(ChargeType.INTERNAL);
            double charge = chargeStrategy3.charge(100);
            System.out.println("内部价格："+charge);

            chargeStrategy3 = AnnotationChargeStrategyFactory2.getChargeStrategy(ChargeType.EXTERNAL);
            charge = chargeStrategy3.charge(100);
            System.out.println("外部价格："+charge);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
