package com.jiangc.practice.messages.messages.rest.facilities;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.StringUtils;

public enum TypeTimeEnum {
    INTEGRAL_ADD("", 1, 9, 21, "添加积分"),
    INTEGRAL_EXPIRE("", 0, 9, 21, "到期积分"),
    INTEGRAL_LESS("TM00342", 2, 0, 25, "消费积分"),
    ESTABLISHMENT_DATE("OPENTM417908134", 3, 10, 20, "开放日"),
    MEMBER_GRADE("OPENTM414995341", 4, 10, 20, "会员等级变更"),
    CONTRACT_SIGNING("OPENTM201266203", 5, 9, 20, "电子合同签署成功/失败"),
    OPEN_DAY_BUY("OPENTM207276764", 6, 10, 20, "开放期(认购)"),
    OPEN_DAY_REDEEM("OPENTM406121014", 7, 10, 20, "开放期(赎回)"),
    FULL_AMOUNT("TM00197", 8, 0, 25, "足额到账"),
    CASH_REMIND("TM00392", 9, 10, 20, "兑付提醒"),
    RAI_SHARE_TIME("TM00773", 10, 9, 20, "分红/分配提醒"),
    PAY_INTEREST("OPENTM417840014", 11, 0, 25, "付息提醒"),
    RISK_ASSESSMENT_MATURITY("OPENTM415847023", 12, 9, 20, "风测到期"),
    LIQUIDATION("OPENTM412820086", 13, 10, 20, "清算提醒");

    private final String templateKey;
    private final Integer type;
    private final Integer beginHour;
    private final Integer endHour;
    private final String message;
    private static final ImmutableMap<Integer, TypeTimeEnum> INT_ENUM_MAP;
    private static final List<TypeTimeEnum> INIT_LIST = new ArrayList();

    public static TypeTimeEnum of(Integer type) {
        return (TypeTimeEnum)INT_ENUM_MAP.get(type);
    }

    public static TypeTimeEnum ofName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        } else {
            TypeTimeEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                TypeTimeEnum typeTimeEnum = var1[var3];
                if (typeTimeEnum.name().equals(name)) {
                    return typeTimeEnum;
                }
            }

            return null;
        }
    }

    public static List<TypeTimeEnum> getAllTypeTimeEnums() {
        return INIT_LIST;
    }

    public String getTemplateKey() {
        return this.templateKey;
    }

    public Integer getType() {
        return this.type;
    }

    public Integer getBeginHour() {
        return this.beginHour;
    }

    public Integer getEndHour() {
        return this.endHour;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "TypeTimeEnum." + this.name() + "(templateKey=" + this.getTemplateKey() + ", type=" + this.getType() + ", beginHour=" + this.getBeginHour() + ", endHour=" + this.getEndHour() + ", message=" + this.getMessage() + ")";
    }

    private TypeTimeEnum(final String templateKey, final Integer type, final Integer beginHour, final Integer endHour, final String message) {
        this.templateKey = templateKey;
        this.type = type;
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.message = message;
    }

    static {
        ImmutableMap.Builder<Integer, TypeTimeEnum> builder = new ImmutableMap.Builder();
        TypeTimeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TypeTimeEnum value = var1[var3];
            builder.put(value.getType(), value);
        }

        INT_ENUM_MAP = builder.build();
        INIT_LIST.addAll(Arrays.asList(values()));
    }
}