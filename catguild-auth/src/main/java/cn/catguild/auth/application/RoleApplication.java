package cn.catguild.auth.application;

import cn.catguild.auth.domain.CatRole;
import cn.catguild.auth.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/7/31 17:48
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class RoleApplication {

	private final RoleRepository roleRepository;

	public void addRole(CatRole catRole) {
		roleRepository.save(catRole);
	}

	public List<CatRole> getByUserId(Long userId) {
		return new ArrayList<>();
	}

	public List<CatRole> getByUserIdAndAppCode(Long userId, String appCode) {
		return new ArrayList<>();
	}


	public void updateRole(Long id, CatRole catRole) {
		catRole.setId(id);
		roleRepository.save(catRole);
	}

	public void removeRole(List<Long> ids) {

	}

}
