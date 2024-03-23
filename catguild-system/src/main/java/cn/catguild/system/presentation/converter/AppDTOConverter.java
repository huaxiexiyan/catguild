package cn.catguild.system.presentation.converter;

import cn.catguild.system.api.AppClientProto;
import cn.catguild.system.api.common.MenuMessageProto;
import cn.catguild.system.domain.App;
import cn.catguild.system.domain.Menu;
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

    @Mapping(source = "id",target = "appId")
    //@Mapping(source = "menus",target = "menusList")
    AppClientProto.AppDTO toDTO(App apps);



    List<AppClientProto.AppDTO> toDTO(List<App> apps);


    @Mapping(source = "menu.id",target = "menuId")
    @Mapping(source = "app.id",target = "appId")
    MenuMessageProto.MenuDTO toMenuDTO(App app, Menu menu);

    @Mapping(source = "menu.id",target = "menuId")
    MenuMessageProto.MenuDTO toMenuDTO(Menu menu);

    List<MenuMessageProto.MenuDTO> toMenuDTO(List<Menu> menus);

    default List<MenuMessageProto.MenuDTO> toMenuDTO(App app, List<Menu> menus) {
        return menus.stream()
                .map(menu -> toMenuDTO(app, menu))
                .toList();
    }

}
