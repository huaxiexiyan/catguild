package cn.catguild.auth.domain;

import cn.catguild.auth.domain.type.MenuMeta;
import cn.catguild.common.entity.jpa.AbstractEntity;
import cn.catguild.common.type.CatTreeNode;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.util.Collection;

/**
 * @author xiyan
 * @date 2023/8/8 17:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_menu`")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Menu extends AbstractEntity implements CatTreeNode<Menu,Long> {

    @Comment("上级菜单，最多3层")
    private Long parentId;

    @Comment("是当前路由的路径，会与配置中的父级节点的 path 组成该页面路由的最终路径；如果需要跳转外部链接，可以将path设置为 http 协议开头的路径。")
    @Column(length = 200)
    private String path;

    @Comment("影响多标签 Tab 页的 keep-alive 的能力，如果要确保页面有 keep-alive 的能力，请保证该路由的name与对应页面（SFC)的name保持一致。")
    @Column(length = 200)
    private String name;

    @Comment("渲染该路由时使用的页面组件")
    @Column(length = 200)
    private String component;

    @Comment("重定向的路径")
    @Column(length = 200)
    private String redirect;

    @Embedded
    private MenuMeta meta;

    @Transient
    private Collection<Menu> children;

    @Transient
    private Long resourceId;

    public String getLabel(){
        return meta.getTitle();
    }

    public Long getValue(){
        return super.getId();
    }

}
