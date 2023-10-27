package cn.catguild.auth.presentation.client.converter;

import cn.catguild.auth.api.PermissionsClientProto;
import cn.catguild.auth.domain.Resource;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthResourceConverter {

    AuthResourceConverter INSTANCE = Mappers.getMapper(AuthResourceConverter.class);


    @Mapping(source = "resourceId",target = "refId")
    @Mapping(source = "resourceType",target = "refType")
    Resource fromData(PermissionsClientProto.AuthResource authResource);

    List<Resource> fromData(List<PermissionsClientProto.AuthResource> authResources);


}
