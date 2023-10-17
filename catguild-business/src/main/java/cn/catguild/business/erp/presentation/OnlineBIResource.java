package cn.catguild.business.erp.presentation;

import cn.catguild.business.erp.application.OnlineBIApplication;
import cn.catguild.business.erp.application.dto.StatisticsChart;
import cn.catguild.business.erp.application.dto.StatisticsType;
import cn.catguild.business.erp.application.dto.TimeScale;
import cn.catguild.business.util.AuthUtil;
import cn.catguild.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2023/10/16 14:13
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/erp/online/bi")
@RestController
public class OnlineBIResource {

    private final OnlineBIApplication onlineBIApplication;

    //线上订单统计趋势 日销售额折线图
    @GetMapping("/sales-line-chart")
    public ApiResponse<StatisticsChart> salesLineChart(@RequestParam("timeScale") TimeScale timeScale,
                                                       @RequestParam("type") StatisticsType type){
        StatisticsChart chart = onlineBIApplication.salesLineChart(AuthUtil.getTenantId(),timeScale,type);
        return ApiResponse.ok(chart);
    }


}
