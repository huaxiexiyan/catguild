package cn.catguild.user.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author xiyan
 * @date 2023/4/9 14:04
 */
@Data
@TableName("Guild")
public class GuildDO extends BaseDO{

	private String name;

	private String domainName;

}
