package cn.catguild.user.service.impl;

import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.mapper.CatUserMapper;
import cn.catguild.user.service.CatUserService;
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


}
