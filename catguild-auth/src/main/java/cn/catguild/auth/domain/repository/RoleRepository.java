package cn.catguild.auth.domain.repository;

import cn.catguild.auth.domain.CatRole;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/19 15:43
 */
public interface RoleRepository {
    List<CatRole> findByAppIdAndUserId(Long tenantId,Long userId, Long appId);

}
