package cn.catguild.auth.infrastructure.repository.converter;

import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.infrastructure.repository.domain.entity.TenantDO;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TenantDataConverter {

    TenantDataConverter INSTANCE = Mappers.getMapper(TenantDataConverter.class);

    Tenant fromData(TenantDO tenantDO);

    List<Tenant> fromData(List<TenantDO> tenantDO);

    TenantDO toData(Tenant tenant);

}
