package cn.catguild.auth.infrastructure.repository;

import cn.catguild.auth.domain.CatRole;
import cn.catguild.auth.domain.repository.RoleRepository;
import cn.catguild.auth.infrastructure.repository.converter.RoleDataConverter;
import cn.catguild.auth.infrastructure.repository.domain.entity.CatRoleDO;
import cn.catguild.auth.infrastructure.repository.mapper.CatRoleDOMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/19 15:49
 */
@Slf4j
@AllArgsConstructor
@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final CatRoleDOMapper baseMapper;

    private final RoleDataConverter roleDataConverter;

    @Override
    public List<CatRole> findByAppIdAndUserId(Long tenantId, Long userId, Long appId) {
        List<CatRoleDO> roles = baseMapper.selectByAppIdAndUserId(tenantId, userId, appId);
        return roles.stream().map(roleDataConverter::fromData).toList();
    }

}
