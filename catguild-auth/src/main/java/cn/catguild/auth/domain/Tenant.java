package cn.catguild.auth.domain;

import cn.catguild.auth.domain.common.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * 租户（以租户为界隔离数据及权限）
 *
 * @author xiyan
 * @date 2023/7/31 15:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_tenant`")
public class Tenant extends AbstractEntity {

    @Id
    private Long id;

    @Comment("租户名")
    private String name;

	@Comment("密码")
	private String password;

}
