package cn.catguild.auth.domain;

import cn.catguild.auth.domain.type.GuildId;
import lombok.Data;

/**
 * 公会实体类
 *
 * @author xiyan
 * @date 2023/5/13 11:47
 */
@Data
public class Guild {

    private GuildId id;

    private String name;

    private String code;

    private String avatar;

    private String registerCountry;

    private String registerProvince;

    private String registerCity;

    private String registerAddress;

    private String status;

    private Long createdTime;

}
