package cn.catguild.auth.infrastructure.repository.impl;

import cn.catguild.auth.domain.CatRole;
import cn.catguild.auth.domain.repository.RoleRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.infrastructure.repository.converter.RoleDataConverter;
import cn.catguild.auth.infrastructure.repository.domain.entity.CatRoleDO;
import cn.catguild.auth.infrastructure.repository.mapper.CatRoleDOMapper;
import cn.catguild.auth.oauth.util.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

	private final IdGenerationClient idClient;

    @Override
    public List<CatRole> findByAppIdAndUserId(Long tenantId, Long userId, Long appId) {
        List<CatRoleDO> roles = baseMapper.selectByAppIdAndUserId(tenantId, userId, appId);
        return roles.stream().map(roleDataConverter::fromData).toList();
    }

	@Override
	public void save(CatRole catRole) {
		Long loginId = AuthUtil.getLoginId();
		CatRoleDO catRoleDO = roleDataConverter.toData(catRole);
		catRoleDO.setTenantId(AuthUtil.getTenantId());
		if (catRoleDO.getId() != null && catRoleDO.getId() > 0) {
			// update
			catRoleDO.setLastModifyBy(loginId);
			catRoleDO.setLastModifyTime(LocalDateTime.now());
			baseMapper.updateById(catRoleDO);
		} else {
			// insert
			catRoleDO.setId(idClient.nextId());
			catRoleDO.setCreateBy(loginId);
			catRoleDO.setCreateTime(LocalDateTime.now());
			baseMapper.insert(catRoleDO);
			catRoleDO.setId(catRoleDO.getId());
		}
	}

	@Override
	public void removeById(Long id) {

	}

}
