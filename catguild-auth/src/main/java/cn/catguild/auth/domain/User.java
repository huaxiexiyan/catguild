package cn.catguild.auth.domain;

import cn.catguild.auth.domain.common.BaseTenant;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/7/31 15:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_user`")
public class User extends BaseTenant {

    @Id
    private Long id;

    @Comment("用户名")
    private String name;

    @Comment("账号id")
    private Long accountId;


}
