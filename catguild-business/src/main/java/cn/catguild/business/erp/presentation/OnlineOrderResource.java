package cn.catguild.business.erp.presentation;

import cn.catguild.business.erp.application.OnlineOrderApplication;
import cn.catguild.business.erp.infrastructure.domain.OnlineOrderDO;
import cn.catguild.business.erp.infrastructure.domain.OnlineOrderItemDO;
import cn.catguild.business.erp.infrastructure.domain.OnlineOrderLogisticsDO;
import cn.catguild.business.erp.infrastructure.domain.query.OnlineOrderQuery;
import cn.catguild.business.erp.infrastructure.domain.type.OrderPlatform;
import cn.catguild.business.erp.presentation.model.OnlineOrderImportRequest;
import cn.catguild.business.util.AuthUtil;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiyan
 * @date 2023/8/8 16:25
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/erp/online/orders")
@RestController
public class OnlineOrderResource {

    private final OnlineOrderApplication onlineOrderApplication;

    /**
     * 最简单的读
     * 3. 直接读即可
     */
    @Async
    @PostMapping("/import")
    public void importOnlineOrder(@RequestParam("file") MultipartFile file) throws IOException {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        Set<String> orderNums = new HashSet<>();
        EasyExcel.read(file.getInputStream(), OnlineOrderImportRequest.class,
                new PageReadListener<OnlineOrderImportRequest>(dataList -> {
                    for (OnlineOrderImportRequest onlineOrderImportRequest : dataList) {
                        OnlineOrderDO onlineOrder = new OnlineOrderDO();
                        BeanUtils.copyProperties(onlineOrderImportRequest, onlineOrder);

                        OnlineOrderLogisticsDO orderLogisticsDO = new OnlineOrderLogisticsDO();
                        BeanUtils.copyProperties(onlineOrderImportRequest, orderLogisticsDO);
                        onlineOrder.setLogisticsInfo(orderLogisticsDO);

                        OnlineOrderItemDO orderItemDO = new OnlineOrderItemDO();
                        BeanUtils.copyProperties(onlineOrderImportRequest, orderItemDO);
                        List<OnlineOrderItemDO> itemDOS = new ArrayList<>();
                        itemDOS.add(orderItemDO);
                        onlineOrder.setOrderItems(itemDOS);

                        if (orderNums.contains(onlineOrderImportRequest.getOrderNum())) {
                            log.info("复合订单号:{}", onlineOrderImportRequest.getOrderNum());
                        }
                        orderNums.add(onlineOrderImportRequest.getOrderNum());
                        onlineOrder.setOrderPlatform(OrderPlatform.PDD);
                        onlineOrderApplication.save(AuthUtil.getLoginId(), onlineOrder);
                    }
                })).sheet().doRead();
    }

    @GetMapping("/")
    public ApiResponse<ApiPage<OnlineOrderDO>> page(@ModelAttribute OnlineOrderQuery query) {
        return ApiResponse.ok(onlineOrderApplication.page(AuthUtil.getTenantId(), query));
    }

}
