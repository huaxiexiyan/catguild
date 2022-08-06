package cn.catguild.user.service.impl;

import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.mapper.AccountMapper;
import cn.catguild.user.service.AccountService;
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
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
