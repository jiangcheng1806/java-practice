package com.jiangcz.application.common.util.excelUtils.export;

import com.jiangcz.application.common.util.excelUtils.excel.ExcelTag;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *  准点率考核报表：订单明细数据
 *  2017-03-3
 */
@Getter
@Setter
public class OrderNodeReport implements Serializable {

	private static final long serialVersionUID = 6132725539031659549L;

	@ExcelTag(index=0, title="序")
	private Integer NO;	//excel中记录序号

	@ExcelTag(index=1, title="订单号")
	private Long orderId;//订单ID
}
