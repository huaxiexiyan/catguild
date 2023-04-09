package cn.catguild.user.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xiyan
 * @date 2023/4/9 14:06
 */
@Data
public class BaseDO {

	@TableId(type = IdType.AUTO)
	private Long id;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

	private LocalDateTime deletedTime;

}
