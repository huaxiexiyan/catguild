package cn.catguild.auth.controller;

import cn.catguild.auth.controller.command.GuildCommand;
import cn.catguild.auth.domain.Guild;
import cn.catguild.auth.domain.type.GuildId;
import cn.catguild.auth.service.GuildService;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 公会资源控制器
 *
 * @author xiyan
 * @date 2023/5/13 11:47
 */
@Slf4j
@AllArgsConstructor
@RequestMapping("/guilds")
@RestController
public class GuildController {

    private GuildService guildService;

    @GetMapping("")
    public ApiResponse<ApiPage<Guild>> page(@RequestBody GuildCommand guildCommand){
        ApiPage<Guild> page = guildService.getPage(guildCommand);
        return ApiResponse.ok(page);
    }

    @GetMapping("/{id}")
    public ApiResponse<Guild> detail(@PathVariable("id") Long id){
        Guild guild = guildService.get(new GuildId(id));
        return ApiResponse.ok(guild);
    }

    @PostMapping("")
    public ApiResponse<Void> add(@RequestBody Guild guild){
        guildService.add(guild);
        return ApiResponse.ok();
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> put(@PathVariable("id") Long id, @RequestBody Guild guild){
        guildService.update(guild);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<Void> patch(@PathVariable("id") Long id, @RequestBody GuildCommand guildCommand){
        guildService.updateByStatus(id,guildCommand);
        return ApiResponse.ok();
    }

}
