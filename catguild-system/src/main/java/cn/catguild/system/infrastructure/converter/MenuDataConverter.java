package cn.catguild.system.infrastructure.converter;

import cn.catguild.system.domain.Menu;
import cn.catguild.system.infrastructure.domain.MenuDO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuDataConverter {

    MenuDataConverter INSTANCE = Mappers.getMapper(MenuDataConverter.class);

    @Mapping(source = "parentId",target = "parentMenu.id")
    @Mapping(source = ".",target = "meta")
    Menu fromData(MenuDO menuDO);

    @Mapping(source = "meta",target = ".")
    MenuDO toData(Menu menu);


}
