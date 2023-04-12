package cn.catguild.user.domain.po;

import cn.catguild.user.domain.type.IdentityType;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户是唯一主体（映射现实的行为人）
 *
 * @author xiyan
 * @date 2022/9/30 15:01
 */
@Data
@TableName("cat_user")
public class CatUserDO extends BaseGuildDO{

	private String name;

	private String sex;

	private String age;

	private IdentityType identityType;

}
