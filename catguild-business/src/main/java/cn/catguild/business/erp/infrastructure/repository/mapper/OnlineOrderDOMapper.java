package cn.catguild.business.erp.infrastructure.repository.mapper;

import cn.catguild.business.erp.infrastructure.domain.OnlineOrderDO;
import cn.catguild.business.erp.infrastructure.domain.dto.OrderStatisticsDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiyan
 * @date 2022-04-03 21:14
 */
public interface OnlineOrderDOMapper extends BaseMapper<OnlineOrderDO> {
    List<OrderStatisticsDTO> calculateSalesLineDay(@Param("tenantId") Long tenantId,
                                                                  @Param("startTime") LocalDateTime startTime,
                                                                  @Param("endTime") LocalDateTime endTime);

}
