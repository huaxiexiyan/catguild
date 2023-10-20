package cn.catguild.business.automatic.infrastructure.repository.mapper;

import cn.catguild.business.automatic.infrastructure.domain.custom.AppAuthConfigCustom;
import cn.catguild.business.automatic.infrastructure.domain.entity.AppAuthConfigDO;
import cn.catguild.business.automatic.infrastructure.domain.query.AppAuthConfigQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * app授权配置实体表 Mapper 接口
 * </p>
 *
 * @author xiyan
 * @since 2023-10-20
 */
public interface AppAuthConfigDOMapper extends BaseMapper<AppAuthConfigDO> {

    IPage<AppAuthConfigCustom> selectCustomPage(@Param("tenantId") Long tenantId,
                                                @Param("ipage") IPage<AppAuthConfigDO> ipage,
                                                @Param("query") AppAuthConfigQuery query);


}
