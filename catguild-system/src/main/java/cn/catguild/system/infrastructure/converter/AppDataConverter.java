package cn.catguild.system.infrastructure.converter;

import cn.catguild.system.domain.App;
import cn.catguild.system.infrastructure.domain.AppDO;
import cn.catguild.system.infrastructure.domain.query.AppQuery;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppDataConverter {

    AppDataConverter INSTANCE = Mappers.getMapper(AppDataConverter.class);

    @Mapping(source = "parentId",target = "parentApp.id")
    App fromData(AppDO appDO);

    List<App> fromData(List<AppDO> appDO);

    AppDO toData(App app);

    AppDO toData(AppQuery appQuery);

}
