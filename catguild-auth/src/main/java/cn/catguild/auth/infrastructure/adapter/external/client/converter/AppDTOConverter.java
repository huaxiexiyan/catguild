package cn.catguild.auth.infrastructure.adapter.external.client.converter;

import cn.catguild.system.api.AppClientProto;
import cn.catguild.system.api.common.MenuMessageProto;
import cn.catguild.system.api.dto.AppDTO;
import cn.catguild.system.api.dto.MenuDTO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppDTOConverter {

    AppDTOConverter INSTANCE = Mappers.getMapper(AppDTOConverter.class);

    @Mapping(source = "menusList",target = "menus")
    AppDTO fromDTO(AppClientProto.AppDTO apps);

    List<AppDTO> fromDTO(List<AppClientProto.AppDTO> apps);

    MenuDTO fromMenuDTO(MenuMessageProto.MenuDTO menu);

    List<MenuDTO> fromMenuDTO(List<MenuMessageProto.MenuDTO> menus);


}
