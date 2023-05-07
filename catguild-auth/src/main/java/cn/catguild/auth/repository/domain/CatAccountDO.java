package cn.catguild.auth.repository.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/5/1 11:46
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CatAccountDO extends BaseGuildDO{

    private String username;

    private String password;

}
