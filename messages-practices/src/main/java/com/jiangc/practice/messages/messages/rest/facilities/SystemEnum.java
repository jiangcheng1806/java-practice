package com.jiangc.practice.messages.messages.rest.facilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public enum SystemEnum {
    SOURCE_POINT(0, "积分服务"),
    UNIFIED_CUSTOMER(1, "统一客户"),
    JI_ELECTRONIC_CONTRACT(2, "电子合同(基金)"),
    JIN_ELECTRONIC_CONTRACT(3, "电子合同(金投)"),
    PRIVATE_SALES(4, "私募销售"),
    GENERAL_MODEL(5, "通用模式"),
    RAISE(6, "募集户资金流水"),
    STOCK_RIGHTS(7, "股权"),
    FUND(8, "基金");

    private static final Logger log = LoggerFactory.getLogger(SystemEnum.class);
    private final Integer type;
    private final String message;

    public static SystemEnum of(Integer type) {
        if (type == null) {
            return null;
        } else {
            SystemEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                SystemEnum value = var1[var3];
                if (value.getType().equals(type)) {
                    return value;
                }
            }

            return null;
        }
    }

    public static SystemEnum ofName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        } else {
            SystemEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                SystemEnum systemEnum = var1[var3];
                if (systemEnum.name().equals(name)) {
                    return systemEnum;
                }
            }

            return null;
        }
    }

    public Integer getType() {
        return this.type;
    }

    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return "SystemEnum." + this.name() + "(type=" + this.getType() + ", message=" + this.getMessage() + ")";
    }

    private SystemEnum(final Integer type, final String message) {
        this.type = type;
        this.message = message;
    }
}