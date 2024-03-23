package cn.catguild.operation.presentation.vo;

import cn.catguild.common.type.CatTreeNode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.Collection;

/**
 * @author xiyan
 * @date 2024/1/18 16:58
 */
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MenuVO implements CatTreeNode<MenuVO,Long> {

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

	private MenuMetaVO meta;

	private Collection<MenuVO> children;

	private Long resourceId;

	public String getLabel(){
		return meta.getTitle();
	}

	public Long getValue(){
		return this.id;
	}

}
