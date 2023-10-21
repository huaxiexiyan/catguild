package cn.catguild.auth.infrastructure.repository.converter;

import cn.catguild.auth.domain.CatRole;
import cn.catguild.auth.infrastructure.repository.entity.CatRoleDO;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleDataConverter {

    RoleDataConverter INSTANCE = Mappers.getMapper(RoleDataConverter.class);

    CatRole fromData(CatRoleDO catRoleDO);

    CatRoleDO toData(CatRole menu);

}
