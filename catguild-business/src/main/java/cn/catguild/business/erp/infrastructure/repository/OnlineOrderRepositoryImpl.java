package cn.catguild.business.erp.infrastructure.repository;

import cn.catguild.business.erp.domain.OnlineOrderRepository;
import cn.catguild.business.erp.infrastructure.domain.OnlineOrderDO;
import cn.catguild.business.erp.infrastructure.domain.OnlineOrderItemDO;
import cn.catguild.business.erp.infrastructure.domain.OnlineOrderLogisticsDO;
import cn.catguild.business.erp.infrastructure.domain.dto.OrderStatisticsDTO;
import cn.catguild.business.erp.infrastructure.domain.query.OnlineOrderQuery;
import cn.catguild.business.erp.infrastructure.repository.jpa.OnlineOrderDORepository;
import cn.catguild.business.erp.infrastructure.repository.jpa.OnlineOrderItemDORepository;
import cn.catguild.business.erp.infrastructure.repository.jpa.OnlineOrderLogisticsDORepository;
import cn.catguild.business.erp.infrastructure.repository.mapper.OnlineOrderDOMapper;
import cn.catguild.business.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.business.util.AuthUtil;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.Kv;
import cn.catguild.common.utility.IPageUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2023/10/10 23:54
 */
@Slf4j
@AllArgsConstructor
@Component
public class OnlineOrderRepositoryImpl implements OnlineOrderRepository {

    private final IdGenerationClient idClient;
    private final OnlineOrderDOMapper baseMapper;

    private OnlineOrderItemDORepository onlineOrderItemRepository;
    private OnlineOrderLogisticsDORepository onlineOrderLogisticsRepository;
    private OnlineOrderDORepository onlineOrderRepository;


    @Override
    public void save(Long tenantId, OnlineOrderDO onlineOrder) {
        // 分门别类保存
        OnlineOrderDO onlineOrderDB = onlineOrderRepository.findByTenantIdAndOrderNum(tenantId, onlineOrder.getOrderNum());
        if (onlineOrderDB == null) {
            onlineOrder.setId(idClient.nextId());
            onlineOrder.setTenantId(tenantId);

            onlineOrder.setCTime(LocalDateTime.now());
            onlineOrder.setCBy(AuthUtil.getLoginId());
        } else {
            onlineOrder.setId(onlineOrderDB.getId());
            onlineOrder.setTenantId(onlineOrderDB.getTenantId());

            onlineOrder.setCBy(onlineOrderDB.getCBy());
            onlineOrder.setCTime(onlineOrderDB.getCTime());
            onlineOrder.setLmTime(LocalDateTime.now());
            onlineOrder.setLmBy(AuthUtil.getLoginId());
        }
        onlineOrderRepository.saveAndFlush(onlineOrder);
        // 保存附属项
        saveOnlineOrderItems(tenantId, onlineOrder.getId(), onlineOrder.getOrderNum(), onlineOrder.getOrderItems());
        saveOnlineOrderLogistics(tenantId, onlineOrder.getId(), onlineOrder.getOrderNum(), onlineOrder.getLogisticsInfo());
    }

    @Override
    public ApiPage<OnlineOrderDO> page(Long tenantId, OnlineOrderQuery query) {
        OnlineOrderDO queryDO = new OnlineOrderDO();
        BeanUtils.copyProperties(query, queryDO);
        IPage<OnlineOrderDO> onlineOrderDOIPage = baseMapper.selectPage(query.getIpage(), Wrappers.query(queryDO));
        return IPageUtils.toApiPage(onlineOrderDOIPage, (iPage) -> (Collection<OnlineOrderDO>) iPage.getRecords());
    }

    @Override
    public List<Kv<LocalDate, BigDecimal>> findSalesLineDaySalesRevenue(Long tenantId, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }

    @Override
    public List<Kv<LocalDate, BigDecimal>> findSalesLineDayOrderQuantity(Long tenantId, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }

    @Override
    public List<Kv<LocalDate, BigDecimal>> findSalesLineDaySalesVolume(Long tenantId, LocalDateTime startTime, LocalDateTime endTime) {
        return null;
    }

    @Override
    public List<Kv<LocalDate, OrderStatisticsDTO>> findSalesLineDay(Long tenantId, LocalDateTime startTime, LocalDateTime endTime) {
        return baseMapper.calculateSalesLineDay(tenantId,startTime,endTime);
    }

    private void saveOnlineOrderItems(Long tenantId,
                                      Long orderId, String orderNum,
                                      List<OnlineOrderItemDO> orderItems) {
        List<OnlineOrderItemDO> itemsDB = onlineOrderItemRepository.findByTenantIdAndOrderId(tenantId, orderId);
        Map<Long, OnlineOrderItemDO> itemMap = itemsDB.stream()
                .collect(Collectors.toMap(OnlineOrderItemDO::getSkuId, Function.identity()));
        orderItems.forEach(item -> {

            // 线上订单导入，只要存在了，就不会删除,则只需修改存在，新增没有的
            if (!CollectionUtils.isEmpty(itemMap) && itemMap.containsKey(item.getSkuId())) {
                // 更新
                OnlineOrderItemDO orderItemDO = itemMap.get(item.getSkuId());
                item.setId(orderItemDO.getId());
                item.setTenantId(orderItemDO.getTenantId());
                item.setOrderId(orderItemDO.getOrderId());
                item.setOrderNum(orderItemDO.getOrderNum());

                item.setLmTime(LocalDateTime.now());
                item.setLmBy(AuthUtil.getLoginId());
            } else {
                // 新增
                item.setId(idClient.nextId());
                item.setTenantId(tenantId);
                item.setOrderId(orderId);
                item.setOrderNum(orderNum);
            }
            onlineOrderItemRepository.saveAndFlush(item);
        });
    }

    private void saveOnlineOrderLogistics(Long tenantId,
                                          Long orderId, String orderNum,
                                          OnlineOrderLogisticsDO logisticsInfo) {
        OnlineOrderLogisticsDO logisticsDB = onlineOrderLogisticsRepository.findByTenantIdAndOrderId(tenantId, orderId);

        if (logisticsDB == null) {
            logisticsInfo.setId(idClient.nextId());
            logisticsInfo.setTenantId(tenantId);
            logisticsInfo.setOrderNum(orderNum);
            logisticsInfo.setOrderId(orderId);

            logisticsInfo.setCTime(LocalDateTime.now());
            logisticsInfo.setCBy(AuthUtil.getLoginId());
        } else {
            logisticsInfo.setId(logisticsDB.getId());
            logisticsInfo.setTenantId(logisticsDB.getTenantId());
            logisticsInfo.setOrderNum(logisticsDB.getOrderNum());
            logisticsInfo.setOrderId(logisticsDB.getOrderId());

            logisticsInfo.setCBy(logisticsDB.getCBy());
            logisticsInfo.setCTime(logisticsDB.getCTime());
            logisticsInfo.setLmTime(LocalDateTime.now());
            logisticsInfo.setLmBy(AuthUtil.getLoginId());
        }
        onlineOrderLogisticsRepository.saveAndFlush(logisticsInfo);
    }

}
