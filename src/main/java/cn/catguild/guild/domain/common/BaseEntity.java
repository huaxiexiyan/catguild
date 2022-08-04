package cn.catguild.guild.domain.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * DO 基类
 *
 * @author xiyan
 * @date 2020/3/6 10:52
 **/
@Data
public class BaseEntity implements Serializable {

	/**
	 * 主键，默认使用雪花id
	 */
	@TableId(type = IdType.ASSIGN_ID)
	private Long id;
	/**
	 * 创建时间
	 **/
	private Long createdTime;
	/**
	 * 最后修改时间
	 **/
	private Long lastModifiedTime;
	/**
	 * 删除时间
	 */
	private Long deletedTime;

}
