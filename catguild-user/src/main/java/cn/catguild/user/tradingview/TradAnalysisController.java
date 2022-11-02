package cn.catguild.user.tradingview;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.user.tradingview.domain.TradeStock;
import cn.catguild.user.tradingview.mapper.TradeStockMapper;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2022/10/22 23:41
 */
@AllArgsConstructor
@RequestMapping("/trade")
@RestController
public class TradAnalysisController {

	private TradeStockMapper tradeStockMapper;

	@NotNull
	private static Object[][] getObjects(List<LocalDate> x, Map<LocalDate, Map<String, TradeStock>> tradeDateMap) {
		Object[][] data = new Object[x.size()+1][];
		data[0] = new Object[6];
		data[0][1] = "^GSPC";
		data[0][2] = "^IXIC";
		data[0][3] = "^RUT";
		data[0][4] = "CL=F";
		data[0][5] = "GC=F";
		for (int i = 0; i < x.size(); i++) {
			data[i + 1] = new Object[data[0].length];
			LocalDate localDate = x.get(i);
			Map<String, TradeStock> tradeCodeMap = tradeDateMap.get(localDate);
			for (int j = 0; j < data[0].length; j++) {
				if (j == 0) {
					data[i + 1][j] = localDate;
				}else {
					TradeStock tradeStockMonth = tradeCodeMap.get(data[0][j].toString());
					if (tradeStockMonth != null){
						data[i + 1][j] = tradeStockMonth.getClose();
					}else {
						data[i + 1][j] = "-";
					}
				}
			}
		}
		return data;
	}

	/**
	 * 返回一张二维数组表
	 * 表头、x轴
	 * /      值
	 * 时间轴
	 *
	 * @return
	 */
	@GetMapping("/usStock")
	public ApiResponse<?> usStockMonthIndex(@RequestParam("date") String date) {
		List<LocalDate> x = new ArrayList<>();
		List<TradeStock> tradeStocks;
		if (date.equals("month")){
			tradeStocks = tradeStockMapper.selectByMonth();
		}else if (date.equals("week")){
			tradeStocks = tradeStockMapper.selectByWeek();
		}else {
			tradeStocks = tradeStockMapper.selectByDay();
		}
		Map<LocalDate, Map<String, TradeStock>>  tradeDateMap = tradeStocks.stream()
			.peek(t -> {
				if (!x.contains(t.getDate())) {
					x.add(t.getDate());
				}
			})
			.collect(Collectors.groupingBy(TradeStock::getDate,
				Collectors.toMap(TradeStock::getCode,Function.identity())));
		// [行][列]
		Object[][] data = getObjects(x, tradeDateMap);
		return ApiResponse.ok(data);
	}

}
