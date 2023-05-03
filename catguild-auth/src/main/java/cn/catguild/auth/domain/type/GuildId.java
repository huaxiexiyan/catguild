package cn.catguild.auth.domain.type;

import lombok.Getter;

/**
 * 公会id主键
 *
 * @author xiyan
 * @date 2023/5/1 11:41
 */
public class GuildId {

    @Getter
    private long id;

    public GuildId(long id){
        this.id = id;
    }

}
