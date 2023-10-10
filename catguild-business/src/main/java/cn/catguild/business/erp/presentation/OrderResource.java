package cn.catguild.business.erp.presentation;

import cn.catguild.business.erp.presentation.model.OrderImportRequest;
import cn.catguild.common.utility.JSONUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xiyan
 * @date 2023/8/8 16:25
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/pingduoduo/orders")
@RestController
public class OrderResource {

    /**
     * 最简单的读
     * 3. 直接读即可
     */
    @PostMapping("/import")
    public void simpleRead(@RequestParam("file") MultipartFile file) throws IOException {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置
        EasyExcel.read(file.getInputStream(), OrderImportRequest.class,
                new PageReadListener<OrderImportRequest>(dataList -> {
            for (OrderImportRequest orderImportRequest : dataList) {
                //OnlineOrderDO onlineOrder = new OnlineOrderDO();
                //BeanUtils.copyProperties(orderImportRequest,onlineOrder);
                log.info("读取到一条数据{}", JSONUtils.toJsonStr(orderImportRequest));
            }
        })).sheet().doRead();

    }

}
