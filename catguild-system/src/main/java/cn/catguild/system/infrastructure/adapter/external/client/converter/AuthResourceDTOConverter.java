package cn.catguild.system.infrastructure.adapter.external.client.converter;

import cn.catguild.auth.api.PermissionsClientProto;
import cn.catguild.system.infrastructure.adapter.external.client.dto.AuthResourceDTO;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthResourceDTOConverter {

    AuthResourceDTOConverter INSTANCE = Mappers.getMapper(AuthResourceDTOConverter.class);

    AuthResourceDTO fromDTO(PermissionsClientProto.AuthResource authResource);

    PermissionsClientProto.AuthResource toDTO(AuthResourceDTO menu);


}
