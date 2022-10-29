package cn.catguild.user.tradingview;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.user.tradingview.domain.TradeView;
import cn.catguild.user.tradingview.mapper.TradeViewMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiyan
 * @date 2022/10/22 23:41
 */
@AllArgsConstructor
@RequestMapping("/trade")
@RestController
public class TradAnalysisController {

	private TradeViewMapper tradeViewMapper;


	@GetMapping("/usStock")
	public ApiResponse<?> usStockIndex(){
		LambdaQueryWrapper<TradeView> queryWrapper = Wrappers.<TradeView>lambdaQuery()
			.orderByAsc(TradeView::getDate);
		List<TradeView> tradeViews = tradeViewMapper.selectList(queryWrapper);
		return ApiResponse.ok(tradeViews);
	}

}
