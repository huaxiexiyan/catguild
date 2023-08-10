package cn.catguild.auth.domain;

import cn.catguild.auth.domain.common.BaseTenant;
import cn.catguild.common.type.CatTreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.util.Collection;

/**
 * @author xiyan
 * @date 2023/8/8 17:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_menu`")
public class Menu extends BaseTenant implements CatTreeNode<Menu,Long> {

    private Long parentId;

    @Column(length = 200)
    private String path;

    @Column(length = 200)
    private String name;

    @Column(length = 200)
    private String component;

    @Column(length = 200)
    private String redirect;

    @Column(length = 200)
    private String metaJson;

    @Transient
    private Collection<Menu> children;

    public JsonNode getMeta() {
        if (this.metaJson == null){
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(this.metaJson);
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON string to JsonNode: " + this.metaJson, e);
        }
    }

}
