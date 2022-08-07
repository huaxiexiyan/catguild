package cn.catguild.user.service.impl;

import cn.catguild.user.configuration.AuthConfig;
import cn.catguild.user.domain.auth.JwtCredentials;
import cn.catguild.user.domain.auth.JwtHeader;
import cn.catguild.user.domain.auth.JwtPayload;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.exception.AuthException;
import cn.catguild.user.mapper.AccountMapper;
import cn.catguild.user.service.AccountService;
import cn.catguild.user.service.CatUserService;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * @author xiyan
 * @date 2022-08-04 17:38
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

	private final PasswordEncoder passwordEncoder;
	private final CatUserService catUserService;

	@Override
	public JwtCredentials authorization(Account account) {
		LambdaQueryWrapper<Account> queryWrapper = Wrappers.lambdaQuery();
		if (CharSequenceUtil.isNotBlank(account.getUsername())){
			queryWrapper.eq(Account::getUsername,account.getUsername());
		}else if (CharSequenceUtil.isNotBlank(account.getEmail())){
			queryWrapper.eq(Account::getUsername,account.getUsername());
		}else if (CharSequenceUtil.isNotBlank(account.getTelephone())){
			queryWrapper.eq(Account::getUsername,account.getUsername());
		}else {
			throw new AuthException("100001","参数异常：缺失必要的登陆凭证信息");
		}
		Account accountResult = baseMapper.selectOne(queryWrapper);
		if (ObjectUtil.isNotNull(accountResult) && passwordEncoder.matches(account.getPassword(),accountResult.getPassword())){
			CatUser catUser = catUserService.getOne(Wrappers.<CatUser>lambdaQuery().eq(CatUser::getAccountId, accountResult.getId()));
			if (ObjectUtil.isNotNull(accountResult)){
				JwtCredentials jwtCredentials = new JwtCredentials();
				JWTSigner jwtSigner = JWTSignerUtil.hs256(AuthConfig.TOKEN_SECRET);
				Map<String,Object> header =  new JwtHeader().setAlg(jwtSigner.getAlgorithmId()).setTyp("JWT").toMap();
				long currentTimeMillis = System.currentTimeMillis();
				Map<String,Object> payload = new JwtPayload()
					.setCatUser(catUser)
					.setJti(UUID.randomUUID().toString())
					.setIat(currentTimeMillis)
					.setExp(currentTimeMillis + 2 * 3600 * 1000)
					.toMap();

				String token = JWTUtil.createToken(header, payload, jwtSigner);
				jwtCredentials.setToken(token);
				return jwtCredentials;
			}
			throw new AuthException("100003","账号异常：用户不存在");
		}
		throw new AuthException("100002","登陆异常：账号或密码错误");
	}

}
