package cn.catguild.user.tradingview.mapper;

import cn.catguild.user.tradingview.domain.TradeStock;

import java.util.List;

/**
 * @author xiyan
 * @date 2022-04-03 21:14
 */
public interface TradeStockMapper{
	List<TradeStock> selectByMonth();
	List<TradeStock> selectByWeek();
	List<TradeStock> selectByDay();

}
