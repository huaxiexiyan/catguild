package cn.catguild.auth.infrastructure.repository.mapper;

import cn.catguild.auth.infrastructure.repository.entity.ResourceDO;
import cn.catguild.common.type.ActiveStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 资源实体表 Mapper 接口
 * </p>
 *
 * @author xiyan
 * @since 2023-10-19
 */
public interface ResourceDOMapper extends BaseMapper<ResourceDO> {

    List<ResourceDO> selectByRoleIdsAndType(@Param("tenantId") Long tenantId, @Param("appId") Long appId,
                                            @Param("roleIds") List<Long> roleIds, @Param("resourceType") String resourceType,
                                            @Param("activeStatus") ActiveStatus ActiveStatus);

}
