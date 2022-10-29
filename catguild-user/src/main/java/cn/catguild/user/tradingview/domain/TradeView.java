package cn.catguild.user.tradingview.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author xiyan
 * @date 2022/10/23 15:08
 */
@TableName("test")
@Data
public class TradeView {

	@TableId
	private Long id;

	private BigDecimal open;

	private BigDecimal high;

	private BigDecimal low;

	private BigDecimal close;

	private BigDecimal adjClose;

	private BigDecimal volume;

	private LocalDate date;

}
