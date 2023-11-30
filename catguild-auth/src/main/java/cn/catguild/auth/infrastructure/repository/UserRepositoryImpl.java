package cn.catguild.auth.infrastructure.repository;

import cn.catguild.auth.domain.CatUser;
import cn.catguild.auth.infrastructure.UserRepository;
import cn.catguild.auth.infrastructure.repository.converter.UserDataConverter;
import cn.catguild.auth.infrastructure.repository.domain.entity.UserDO;
import cn.catguild.auth.infrastructure.repository.mapper.UserDOMapper;
import cn.catguild.auth.presentation.model.UserQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.utility.IPageUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/27 14:20
 */
@AllArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserDOMapper baseMapper;

    private final UserDataConverter baseDataConverter;

    //private final IdGenerationClient idClient;

    @SuppressWarnings("unchecked")
    @Override
    public ApiPage<CatUser> page(UserQuery query) {
        IPage<UserDO> page = baseMapper.selectPage(query.getIpage(), Wrappers.emptyWrapper());
        return IPageUtils.toApiPage(page, (iPage) -> {
            List<UserDO> records = (List<UserDO>) iPage.getRecords();
            return records.stream()
                    .map(baseDataConverter::fromData)
                    .toList();
        });
    }

    @Override
    public void save(CatUser user) {

    }

    @Override
    public CatUser findById(Long userId) {
        UserDO userDO = baseMapper.selectById(userId);
        return baseDataConverter.fromData(userDO);
    }


}
