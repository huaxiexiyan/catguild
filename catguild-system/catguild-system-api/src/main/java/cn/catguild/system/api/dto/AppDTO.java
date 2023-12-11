package cn.catguild.system.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/31 16:21
 */
@Data
public class AppDTO {

    private Long appId;

    private List<MenuDTO> menus;

}
