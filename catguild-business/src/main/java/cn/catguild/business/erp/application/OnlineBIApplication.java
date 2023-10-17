package cn.catguild.business.erp.application;

import cn.catguild.business.erp.application.dto.StatisticsChart;
import cn.catguild.business.erp.application.dto.StatisticsType;
import cn.catguild.business.erp.application.dto.TimeScale;
import cn.catguild.business.erp.domain.OnlineOrderRepository;
import cn.catguild.business.erp.infrastructure.domain.dto.OrderStatisticsDTO;
import cn.catguild.common.type.Kv;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/16 14:56
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class OnlineBIApplication {

    private final OnlineOrderRepository onlineOrderRepository;

    public StatisticsChart salesLineChart(Long tenantId, TimeScale timeScale, StatisticsType type) {
        // 1、根据实际类型，获取横坐标轴
        // 2、缓存日销售情况，具体然后再在程序中进行统计
        List<LocalDate> hAxis = getHorizontalAxis(timeScale);
        // 2、根据坐标范围，查询数据
        List<Kv<LocalDate, OrderStatisticsDTO>> lineDayData = getSalesLineDay(tenantId,
                hAxis.get(0).atStartOfDay(), hAxis.get(hAxis.size() - 1).atStartOfDay());
        // 3、根据横坐标合并统计数据
        StatisticsChart chart = new StatisticsChart();
        //计算统计量
        calculateTotal(lineDayData, chart);
        // 计算折线图
        calculateChartPoint(timeScale, type, hAxis, lineDayData, chart);
        chart.setTimeScale(timeScale);
        chart.setType(type);
        return chart;
    }

    private static void calculateTotal(List<Kv<LocalDate, OrderStatisticsDTO>> lineDayData, StatisticsChart chart) {
        OrderStatisticsDTO total = new OrderStatisticsDTO();
        total.initZero();
        lineDayData.forEach(kv -> {
            OrderStatisticsDTO v = kv.getV();
            total.setSalesRevenue(total.getSalesRevenue().add(v.getSalesRevenue()));
            total.setSalesVolume(total.getSalesVolume().add(v.getSalesVolume()));
            total.setOrderQuantity(total.getOrderQuantity() + v.getOrderQuantity());
        });
        chart.setSalesRevenue(total.getSalesRevenue());
        chart.setSalesVolume(total.getSalesVolume());
        chart.setOrderQuantity(total.getOrderQuantity());
    }

    private void calculateChartPoint(TimeScale timeScale, StatisticsType type,
                                     List<LocalDate> hAxis, List<Kv<LocalDate, OrderStatisticsDTO>> lineDayData,
                                     StatisticsChart chart) {
        LinkedHashMap<String, BigDecimal> lineData = new LinkedHashMap<>();
        hAxis.forEach(x -> {
            String displayKey = getDisplay(x, timeScale);
            lineDayData.forEach(kv -> {
                if (x.isEqual(kv.getK())) {
                    BigDecimal value = BigDecimal.ZERO;
                    switch (type) {
                        case SALES_VOLUME -> value = kv.getV().getSalesVolume();
                        case ORDER_QUANTITY -> value = BigDecimal.valueOf(kv.getV().getOrderQuantity());
                        case SALES_REVENUE -> value = kv.getV().getSalesRevenue();
                    }
                    lineData.put(displayKey, value);
                }
            });
            if (!lineData.containsKey(displayKey)) {
                lineData.put(displayKey, BigDecimal.ZERO);
            }
        });
        chart.setData(lineData);
    }

    private List<LocalDate> getHorizontalAxis(TimeScale timeScale) {
        List<LocalDate> hAxis = new LinkedList<>();
        LocalDate now = LocalDate.now();
        switch (timeScale) {
            case WEEK -> {
                LocalDate startDate = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate endDate = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                for (LocalDate day = startDate; !day.isAfter(endDate); day = day.plusDays(1)) {
                    hAxis.add(day);
                }
            }
            case LAST_WEEK -> {
                LocalDate lastMonthNow = LocalDate.now().minusWeeks(1);
                LocalDate startDate = lastMonthNow.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                LocalDate endDate = lastMonthNow.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                for (LocalDate day = startDate; !day.isAfter(endDate); day = day.plusDays(1)) {
                    hAxis.add(day);
                }
            }
            case MONTH -> {
                LocalDate startDate = now.with(TemporalAdjusters.firstDayOfMonth());
                LocalDate endDate = now.with(TemporalAdjusters.lastDayOfMonth());
                for (LocalDate day = startDate; !day.isAfter(endDate); day = day.plusDays(1)) {
                    hAxis.add(day);
                }
            }
            case LAST_MONTH -> {
                LocalDate lastMonthNow = LocalDate.now().minusMonths(1);
                LocalDate startDate = lastMonthNow.with(TemporalAdjusters.firstDayOfMonth());
                LocalDate endDate = lastMonthNow.with(TemporalAdjusters.lastDayOfMonth());
                for (LocalDate day = startDate; !day.isAfter(endDate); day = day.plusDays(1)) {
                    hAxis.add(day);
                }
            }
        }
        return hAxis;
    }

    private List<Kv<LocalDate, OrderStatisticsDTO>> getSalesLineDay(Long tenantId,
                                                                    LocalDateTime startTime, LocalDateTime endTime) {
        return onlineOrderRepository.findSalesLineDay(tenantId, startTime, endTime);
    }

    private String getDisplay(LocalDate localDate, TimeScale timeScale) {
        //本周（周一-周日）、本月（1-31日）、上月、全年（1-12月）
        return switch (timeScale) {
            case WEEK -> getWeekText(localDate.getDayOfWeek());
            case MONTH, LAST_MONTH -> localDate.getDayOfMonth() + "日";
            case YEAR -> localDate.getMonth().getValue() + "月";
            default -> "";
        };
    }

    private String getWeekText(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek.getValue()) {
            case 1 -> "周一";
            case 2 -> "周二";
            case 3 -> "周三";
            case 4 -> "周四";
            case 5 -> "周五";
            case 6 -> "周六";
            case 7 -> "周日";
            default -> "";
        };
    }

}
