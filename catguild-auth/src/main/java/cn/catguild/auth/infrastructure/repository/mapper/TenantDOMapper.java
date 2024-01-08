package cn.catguild.auth.infrastructure.repository.mapper;

import cn.catguild.auth.infrastructure.repository.domain.entity.TenantDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色资源关联关系表 Mapper 接口
 * </p>
 *
 * @author xiyan
 * @since 2023-10-27
 */
public interface TenantDOMapper extends BaseMapper<TenantDO> {

    TenantDO selectByDomainName(@Param("domainName") String domainName);

}
