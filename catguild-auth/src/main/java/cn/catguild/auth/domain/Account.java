package cn.catguild.auth.domain;

import cn.catguild.auth.domain.common.BaseTenant;
import cn.catguild.common.type.YesNoStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/7/31 15:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_account`")
public class Account extends BaseTenant {

    @Id
    private Long id;

    @Comment("登录凭证")
    private String username;

    @Comment("登录密码")
    private String password;

    @Comment("用户id")
    private Long userId;

	@Comment("是否被禁用")
	@Enumerated(EnumType.STRING)
	private YesNoStatus disabled;

}
