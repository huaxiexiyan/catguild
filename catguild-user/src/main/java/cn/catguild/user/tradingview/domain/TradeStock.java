package cn.catguild.user.tradingview.domain;

import cn.catguild.user.tradingview.type.TradeStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author xiyan
 * @date 2022/11/2 13:05
 */
@Data
public class TradeStock {

	private Long id;

	private String code;

	private BigDecimal open;

	private BigDecimal high;

	private BigDecimal low;

	private BigDecimal close;

	private BigDecimal adjClose;

	private BigDecimal volume;

	private LocalDate date;

	private TradeStatus status;

}
