package cn.catguild.guild.service.impl;

import cn.catguild.guild.domain.entity.CatUser;
import cn.catguild.guild.mapper.CatUserMapper;
import cn.catguild.guild.service.CatUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiyan
 * @date 2022-08-04 17:38
 */
@Slf4j
@Service
@AllArgsConstructor
public class CatUserServiceImpl extends ServiceImpl<CatUserMapper, CatUser> implements CatUserService {

	@Override
	public void bindingAccount(Long catUserId, Long accountId) {

	}

}
