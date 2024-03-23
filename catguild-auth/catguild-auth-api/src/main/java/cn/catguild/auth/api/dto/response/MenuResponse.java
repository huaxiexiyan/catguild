package cn.catguild.auth.api.dto.response;

import cn.catguild.common.type.CatTreeNode;
import lombok.Data;
import lombok.ToString;

import java.util.Collection;

/**
 * @author xiyan
 * @date 2024/1/18 10:32
 */
@ToString
@Data
public class MenuResponse  implements CatTreeNode<MenuResponse,Long> {

	private Long id;

	// 上级菜单，最多3层
	private Long parentId;

	// 是当前路由的路径，会与配置中的父级节点的 path 组成该页面路由的最终路径；如果需要跳转外部链接，可以将path设置为 http 协议开头的路径。
	private String path;

	// 影响多标签 Tab 页的 keep-alive 的能力，如果要确保页面有 keep-alive 的能力，请保证该路由的name与对应页面（SFC)的name保持一致。
	private String name;

	// 渲染该路由时使用的页面组件
	private String component;

	// 重定向的路径
	private String redirect;

	// =====  展示参数 ======
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

	private Collection<MenuResponse> children;

}
