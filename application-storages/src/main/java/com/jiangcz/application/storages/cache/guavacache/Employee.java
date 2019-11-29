package com.jiangcz.application.storages.cache.guavacache;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 类名称：Employee<br>
 * 类描述：<br>
 * 创建时间：2018年08月10日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class Employee {
    private final String name;
    private final String dept;
    private final String empID;
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Name", this.getName()).add("Department", getDept())
                .add("EmployeeID", this.getEmpID()).toString();
    }
}
