package cn.catguild.auth.infrastructure.repository.converter;

import cn.catguild.auth.domain.CatUser;
import cn.catguild.auth.infrastructure.repository.domain.entity.UserDO;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/13 14:37
 */
@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDataConverter {

    UserDataConverter INSTANCE = Mappers.getMapper(UserDataConverter.class);

    CatUser fromData(UserDO userDO);

    List<CatUser> fromData(List<UserDO> userDO);

    UserDO toData(CatUser catUser);

}
