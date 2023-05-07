package cn.catguild.auth.domain;

import cn.catguild.auth.domain.type.GuildId;
import lombok.Data;

/**
 * @author xiyan
 * @date 2023/5/7 21:00
 */
@Data
public class Guild {

    private GuildId id;

    private String name;

}
