package cn.catguild.system.domain;


import cn.catguild.common.type.ActiveStatus;
import cn.catguild.common.type.CatTreeNode;
import cn.catguild.system.domain.type.MenuMeta;
import cn.catguild.system.infrastructure.domain.type.MenuType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Collection;

/**
 * @author xiyan
 * @date 2023/8/8 17:10
 */
@Data
public class Menu implements CatTreeNode<Menu, Long> {

    /**
     * 菜单id
     */
    private Long id;

    /**
     * 上级菜单
     */
    private Menu parentMenu;


    /**
     * 菜单类型
     */
    private MenuType type;

    /**
     * 是当前路由的路径，会与配置中的父级节点的 path 组成该页面路由的最终路径；
     * 如果需要跳转外部链接，可以将path设置为 http 协议开头的路径。
     */
    private String path;

    /**
     * 影响多标签 Tab 页的 keep-alive 的能力，如果要确保页面有 keep-alive 的能力，
     * 请保证该路由的name与对应页面（SFC)的name保持一致。
     */
    private String name;

    /**
     * 渲染该路由时使用的页面组件（@位置）
     */
    private String component;

    /**
     * 重定向的路径
     */
    private String redirect;

    /**
     * 菜单展示属性
     */
    private MenuMeta meta;

    private Collection<Menu> children;

    /**
     * 活跃状态
     */
    private ActiveStatus activeStatus;

    public String getLabel() {
        return meta == null ? null:meta.getTitle();
    }

    public Long getValue() {
        return this.id;
    }

    @Override
    public Long getParentId() {
        return parentMenu == null ? 0L : parentMenu.getId() == null ? 0L : parentMenu.getId();
    }

    @JsonIgnore
    public boolean hasParentMenu(){
        return getParentId() != 0L;
    }

}
