package cn.catguild.operation.domain.type;

import lombok.Data;

/**
 * // meta 主要用途是路由在菜单上展示的效果的配置
 *
 * @author xiyan
 * @date 2023/8/25 17:27
 */
@Data
public class MenuMeta {

	// 该路由在菜单上展示的标题
	private String title;

	// 该路由在菜单上展示的图标
	private String icon;

	// 决定该路由在菜单上是否默认展开
	private Boolean expanded;

	// 该路由在菜单上展示先后顺序，数字越小越靠前，默认为零
	private Integer orderNo;

	// 决定该路由是否在菜单上进行展示
	private Boolean hidden;

	// 如果启用了面包屑，决定该路由是否在面包屑上进行展示
	private Boolean hiddenBreadcrumb;

	// 如果是多级菜单且只存在一个节点，想在菜单上只展示一级节点，可以使用该配置。请注意该配置需配置在父节点
	private Boolean single;

	// 内嵌 iframe 的地址
	private String frameSrc;

	// 内嵌 iframe 的地址是否以新窗口打开
	private Boolean frameBlank;

	// 可决定路由是否开启keep-alive，默认开启
	private Boolean keepAlive;

}
