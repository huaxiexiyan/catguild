package cn.catguild.auth.infrastructure.repository.mapper;

import cn.catguild.auth.infrastructure.repository.entity.CatRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * <p>
 * 角色实体表 Mapper 接口
 * </p>
 *
 * @author xiyan
 * @since 2023-10-19
 */
public interface CatRoleDOMapper extends BaseMapper<CatRoleDO> {

    List<CatRoleDO> selectByAppIdAndUserId(@Param("tenantId") Long tenantId,
                                           @Param("userId") Long userId, @Param("appId") Long appId);

}
