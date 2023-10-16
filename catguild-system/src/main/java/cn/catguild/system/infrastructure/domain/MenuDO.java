package cn.catguild.system.infrastructure.domain;


import cn.catguild.common.entity.jpa.AbstractEntity;
import cn.catguild.common.type.ActiveStatus;
import cn.catguild.system.infrastructure.domain.type.MenuType;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author xiyan
 * @date 2023/8/8 17:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`system_menu`")
@TableName("system_menu")
@Comment("系统菜单按钮表")
@DynamicUpdate
public class MenuDO extends AbstractEntity{

    @Comment("上级菜单，最多3层")
    private Long parentId;

    @Comment("层级关联路径，以/分隔")
    @Column(length = 200)
    private String parentPath;

    @Comment("菜单类型")
    @Enumerated(EnumType.STRING)
    private MenuType type;

    @Comment("层级从0开始")
    private Integer level;

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

    @Comment("该路由在菜单上展示的标题")
    @Column(length = 200)
    private String title;

    @Comment("该路由在菜单上展示的图标")
    @Column(length = 200)
    private String icon;

    @Comment("决定该路由在菜单上是否默认展开")
    private Boolean expanded;

    @Comment("该路由在菜单上展示先后顺序，数字越小越靠前，默认为零")
    private Integer orderNo;

    @Comment("决定该路由是否在菜单上进行展示")
    private Boolean hidden;

    @Comment("如果启用了面包屑，决定该路由是否在面包屑上进行展示")
    private Boolean hiddenBreadcrumb;

    @Comment("如果是多级菜单且只存在一个节点，想在菜单上只展示一级节点，可以使用该配置。请注意该配置需配置在父节点")
    private Boolean single;

    @Comment("内嵌 iframe 的地址")
    private String frameSrc;

    @Comment("内嵌 iframe 的地址是否以新窗口打开")
    private Boolean frameBlank;

    @Comment("可决定路由是否开启keep-alive，默认开启")
    private Boolean keepAlive;

    @Comment("活跃状态")
    @Enumerated(EnumType.STRING)
    private ActiveStatus activeStatus;

}
