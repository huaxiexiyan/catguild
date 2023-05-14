package cn.catguild.auth.domain.type;

import lombok.Data;

/**
 * @author xiyan
 * @date 2023/5/14 23:15
 */
@Data
public class GuildId {

    private Long value;

    public GuildId(Long value){
        this.value = value;
    }

}
