package cn.catguild.auth.presentation.model;

import cn.catguild.auth.domain.App;
import cn.catguild.common.api.ApiPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/8/22 15:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppQuery extends ApiPage<App> {
}
