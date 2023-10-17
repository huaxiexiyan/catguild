package cn.catguild.business.erp.presentation.converter;

import cn.catguild.business.erp.infrastructure.domain.type.OrderStatus;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiyan
 * @date 2023/10/10 21:56
 */
@Slf4j
public class OrderStatusConverter implements Converter<OrderStatus> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 这里读的时候会调用
     *
     * @param context
     * @return
     */
    @Override
    public OrderStatus convertToJavaData(ReadConverterContext<?> context) {
        return OrderStatus.parseChineseChar(context.getReadCellData().getStringValue());
    }

    /**
     * 这里是写的时候会调用 不用管
     *
     * @return
     */
    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<OrderStatus> context) {
        return new WriteCellData<>(context.getValue().getChineseChar());
    }

}
