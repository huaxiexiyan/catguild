package cn.catguild.auth.controller.command;

import cn.catguild.auth.domain.Guild;
import cn.catguild.common.api.ApiPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/5/14 17:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GuildCommand extends ApiPage<Guild> {

    private String status;

}
