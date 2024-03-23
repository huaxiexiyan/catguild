package cn.catguild.system.domain;

import cn.catguild.common.domain.BaseBO;
import cn.catguild.common.type.ActiveStatus;
import cn.catguild.common.type.CatTreeNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 应用表 relation ship correlation 关系表 关联表
 *
 * @author xiyan
 * @date 2023/8/22 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class App extends BaseBO implements CatTreeNode<App, Long> {

    private Long id;

    /**
     * 上层应用id
     */
    private App parentApp;

    /**
     * 应用名称
     */
    private String name;

    private String versionName;

    /**
     * 描述
     */
    private String describe;

    /**
     * 活跃状态
     */
    private ActiveStatus activeStatus;

    /**
     * 具体版本
     */
    private Collection<App> children;

    /**
     * 所属菜单
     */
    private List<Menu> menus;

    private LocalDateTime cTime;

    private boolean hasVersion() {
        return getParentId() != null && getParentId() != 0;
    }

    @JsonIgnore
    @Override
    public Long getParentId() {
        return parentApp == null ? null : parentApp.getId();
    }

    @Override
    public void setChildren(Collection<App> children) {
        //children.forEach(v -> v.setVersionName(this.name + StringPool.DASH + v.getName()));
        this.children = children;
    }

    @JsonIgnore
    public void switchActiveStatus() {
        if (this.activeStatus == ActiveStatus.ACTIVE){
            this.activeStatus = ActiveStatus.INACTIVE;
        }else {
            this.activeStatus = ActiveStatus.ACTIVE;
        }
    }

    public String getLabel() {
        return name;
    }

    public Long getValue() {
        return getId();
    }

}
