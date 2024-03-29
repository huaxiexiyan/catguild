package cn.catguild.auth.infrastructure.repository.converter;

import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.infrastructure.repository.domain.entity.ResourceDO;
import cn.catguild.auth.presentation.model.ResourceQuery;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ResourceDataConverter {

    ResourceDataConverter INSTANCE = Mappers.getMapper(ResourceDataConverter.class);

    Resource fromData(ResourceDO resourceDO);

    List<Resource> fromData(List<ResourceDO> resourceDO);

    ResourceDO toData(Resource resource);

    ResourceDO toData(ResourceQuery resourceQuery);

}
