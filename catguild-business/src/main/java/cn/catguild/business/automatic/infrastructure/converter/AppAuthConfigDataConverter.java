package cn.catguild.business.automatic.infrastructure.converter;

import cn.catguild.business.automatic.domain.AppAuthConfig;
import cn.catguild.business.automatic.infrastructure.domain.custom.AppAuthConfigCustom;
import cn.catguild.business.automatic.infrastructure.domain.entity.AppAuthConfigDO;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppAuthConfigDataConverter {

    AppAuthConfigDataConverter INSTANCE = Mappers.getMapper(AppAuthConfigDataConverter.class);

    @Mapping(source = "appAuthTypeId",target = "appAuthType.id")
    AppAuthConfig fromData(AppAuthConfigDO appAuthConfigDO);

    @Mapping(source = "appAuthTypeId",target = "appAuthType.id")
    @Mapping(source = "appAuthTypeName",target = "appAuthType.name")
    AppAuthConfig fromData(AppAuthConfigCustom appAuthConfigCustom);

    @Mapping(source = "appAuthType.id",target = "appAuthTypeId")
    AppAuthConfigDO toData(AppAuthConfig appAuthConfig);


}
