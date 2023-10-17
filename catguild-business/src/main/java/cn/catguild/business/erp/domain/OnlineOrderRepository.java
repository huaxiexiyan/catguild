package cn.catguild.business.erp.domain;

import cn.catguild.business.erp.infrastructure.domain.OnlineOrderDO;
import cn.catguild.business.erp.infrastructure.domain.dto.OrderStatisticsDTO;
import cn.catguild.business.erp.infrastructure.domain.query.OnlineOrderQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.Kv;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/10 21:23
 */
public interface OnlineOrderRepository {
    void save(Long tenantId, OnlineOrderDO onlineOrder);

    ApiPage<OnlineOrderDO> page(Long tenantId, OnlineOrderQuery query);

    List<Kv<LocalDate, BigDecimal>> findSalesLineDaySalesRevenue(Long tenantId, LocalDateTime startTime, LocalDateTime endTime);

    List<Kv<LocalDate, BigDecimal>> findSalesLineDayOrderQuantity(Long tenantId, LocalDateTime startTime, LocalDateTime endTime);

    List<Kv<LocalDate, BigDecimal>> findSalesLineDaySalesVolume(Long tenantId, LocalDateTime startTime, LocalDateTime endTime);

    List<Kv<LocalDate, OrderStatisticsDTO>> findSalesLineDay(Long tenantId, LocalDateTime startTime, LocalDateTime endTime);

}
