package com.jiangc.practice.messages.messages.rest.facilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum MotSourceEnum {

    UNI_MEMBER_GRADE(0, SystemEnum.UNIFIED_CUSTOMER, "等级变更(统一客户)", new TypeTimeEnum[]{TypeTimeEnum.MEMBER_GRADE}),
    JI_CONTRACT_SIGNING(1, SystemEnum.JI_ELECTRONIC_CONTRACT, "电子合同签署成功/失败(基金)", new TypeTimeEnum[]{TypeTimeEnum.CONTRACT_SIGNING}),
    JIN_CONTRACT_SIGNING(2, SystemEnum.JIN_ELECTRONIC_CONTRACT, "电子合同签署成功/失败(金投)", new TypeTimeEnum[]{TypeTimeEnum.CONTRACT_SIGNING}),
    PRI_ESTABLISHMENT_DATE(3, SystemEnum.PRIVATE_SALES, "成立日(私募销售)", new TypeTimeEnum[]{TypeTimeEnum.ESTABLISHMENT_DATE}),
    GEN_ESTABLISHMENT_DATE(4, SystemEnum.GENERAL_MODEL, "成立日(通用模式)", new TypeTimeEnum[]{TypeTimeEnum.ESTABLISHMENT_DATE}),
    PRI_OPEN_DAY(5, SystemEnum.PRIVATE_SALES, "开放期(私募销售)", new TypeTimeEnum[]{TypeTimeEnum.OPEN_DAY_BUY, TypeTimeEnum.OPEN_DAY_REDEEM}),
    PRI_FULL_AMOUNT(6, SystemEnum.PRIVATE_SALES, "足额到账(私募销售)", new TypeTimeEnum[]{TypeTimeEnum.FULL_AMOUNT}),
    GEN_FULL_AMOUNT(7, SystemEnum.GENERAL_MODEL, "足额到账(通用模式)", new TypeTimeEnum[]{TypeTimeEnum.FULL_AMOUNT}),
    PRI_CASH_REMIND(8, SystemEnum.PRIVATE_SALES, "兑付提醒(私募销售)", new TypeTimeEnum[]{TypeTimeEnum.CASH_REMIND}),
    GEN_CASH_REMIND(9, SystemEnum.GENERAL_MODEL, "兑付提醒(通用模式)", new TypeTimeEnum[]{TypeTimeEnum.CASH_REMIND}),
    RAI_SHARE(10, SystemEnum.RAISE, "分红/分配提醒(募集户资金流水)", new TypeTimeEnum[]{TypeTimeEnum.RAI_SHARE_TIME}),
    POINT_CONSUME(11, SystemEnum.SOURCE_POINT, "积分消费提醒（积分服务）", new TypeTimeEnum[]{TypeTimeEnum.INTEGRAL_LESS}),
    POINT_EXPIRE(12, SystemEnum.SOURCE_POINT, "积分到期提醒（积分服务）", new TypeTimeEnum[]{TypeTimeEnum.INTEGRAL_EXPIRE}),
    POINT_ACCOUNT(13, SystemEnum.SOURCE_POINT, "积分新增提醒（积分服务）", new TypeTimeEnum[]{TypeTimeEnum.INTEGRAL_ADD}),
    GEN_PAY_INTEREST(14, SystemEnum.GENERAL_MODEL, "付息提醒（通用模式）", new TypeTimeEnum[]{TypeTimeEnum.PAY_INTEREST}),
    RISK_ASSESSMENT_MATURITY_FUND(15, SystemEnum.FUND, "风测到期（基金）", new TypeTimeEnum[]{TypeTimeEnum.RISK_ASSESSMENT_MATURITY}),
    RISK_ASSESSMENT_MATURITY_GENERAL_MODEL(16, SystemEnum.GENERAL_MODEL, "风测到期（通用模式）", new TypeTimeEnum[]{TypeTimeEnum.RISK_ASSESSMENT_MATURITY}),
    RISK_ASSESSMENT_MATURITY_STOCK_RIGHTS(17, SystemEnum.STOCK_RIGHTS, "风测到期（股权）", new TypeTimeEnum[]{TypeTimeEnum.RISK_ASSESSMENT_MATURITY}),
    GEN_LIQUIDATION(18, SystemEnum.GENERAL_MODEL, "清算提醒（通用模式）", new TypeTimeEnum[]{TypeTimeEnum.LIQUIDATION});

    private static final Logger log = LoggerFactory.getLogger(MotSourceEnum.class);
    private final Integer type;
    private final SystemEnum systemEnum;
    private final String message;
    private final TypeTimeEnum[] typeTimeEnumArray;
    private static final ImmutableMap<Integer, MotSourceEnum> INT_ENUM_MAP;
    private static final List<MotSourceEnum> INIT_LIST = new ArrayList();
    private static final List<Map<String, Object>> INIT_TYPE_MESSAGE_LIST = new ArrayList();

    public static MotSourceEnum of(Integer type) {
        return (MotSourceEnum)INT_ENUM_MAP.get(type);
    }

    public static List<MotSourceEnum> getMotSourceEnums() {
        return INIT_LIST;
    }

    public static List<Map<String, Object>> getTypeMessageList() {
        return INIT_TYPE_MESSAGE_LIST;
    }

    public Integer getType() {
        return this.type;
    }

    public SystemEnum getSystemEnum() {
        return this.systemEnum;
    }

    public String getMessage() {
        return this.message;
    }

    public TypeTimeEnum[] getTypeTimeEnumArray() {
        return this.typeTimeEnumArray;
    }

    public String toString() {
        return "MotSourceEnum." + this.name() + "(type=" + this.getType() + ", systemEnum=" + this.getSystemEnum() + ", message=" + this.getMessage() + ", typeTimeEnumArray=" + Arrays.deepToString(this.getTypeTimeEnumArray()) + ")";
    }

    private MotSourceEnum(final Integer type, final SystemEnum systemEnum, final String message, final TypeTimeEnum[] typeTimeEnumArray) {
        this.type = type;
        this.systemEnum = systemEnum;
        this.message = message;
        this.typeTimeEnumArray = typeTimeEnumArray;
    }

    static {
        ImmutableMap.Builder<Integer, MotSourceEnum> builder = new ImmutableMap.Builder();
        MotSourceEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            MotSourceEnum value = var1[var3];
            builder.put(value.getType(), value);
            Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
            map.put("type", value.getType());
            map.put("name", value.getMessage());
            INIT_TYPE_MESSAGE_LIST.add(map);
        }

        INT_ENUM_MAP = builder.build();
        INIT_LIST.addAll(Arrays.asList(values()));
    }
}
