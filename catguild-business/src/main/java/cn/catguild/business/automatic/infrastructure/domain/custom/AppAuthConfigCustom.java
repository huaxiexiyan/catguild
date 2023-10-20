package cn.catguild.business.automatic.infrastructure.domain.custom;

import cn.catguild.business.automatic.infrastructure.domain.entity.AppAuthConfigDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/10/20 16:09
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class AppAuthConfigCustom extends AppAuthConfigDO {

    private String appAuthTypeName;

}
