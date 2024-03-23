package cn.catguild.operation.presentation.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 * @author xiyan
 * @date 2024/1/18 16:58
 */
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MenuPageVO {

	private Long id;

	// 上级菜单，最多3层
	private Long parentId;

	// 是当前路由的路径，会与配置中的父级节点的 path 组成该页面路由的最终路径；如果需要跳转外部链接，可以将path设置为 http 协议开头的路径。
	private String path;

	// 影响多标签 Tab 页的 keep-alive 的能力，如果要确保页面有 keep-alive 的能力，请保证该路由的name与对应页面（SFC)的name保持一致。
	private String name;

	// 菜单路径
	private String component;

	// 菜单名称
	private String title;

	// 菜单图标
	private String icon;

	// 决定该路由在菜单上是否默认展开
	private Boolean expanded;

	// 菜单顺序
	private Integer orderNo;

	// 是否可见
	private Boolean hidden;

}
