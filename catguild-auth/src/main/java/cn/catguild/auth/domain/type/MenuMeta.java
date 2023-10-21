package cn.catguild.auth.domain.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.hibernate.annotations.Comment;

/**
 *
 * // meta 主要用途是路由在菜单上展示的效果的配置
 *
 * @author xiyan
 * @date 2023/8/25 17:27
 */
@Data
@Embeddable
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuMeta {

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

}
