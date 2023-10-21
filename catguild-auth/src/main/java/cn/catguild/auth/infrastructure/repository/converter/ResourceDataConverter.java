package cn.catguild.auth.infrastructure.repository.converter;

import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.infrastructure.repository.entity.ResourceDO;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResourceDataConverter {

    ResourceDataConverter INSTANCE = Mappers.getMapper(ResourceDataConverter.class);

    Resource fromData(ResourceDO resourceDO);

    ResourceDO toData(Resource resource);

}
