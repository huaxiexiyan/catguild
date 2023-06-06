//package cn.catguild.auth.controller;
//
//import cn.catguild.auth.controller.command.GuildCommand;
//import cn.catguild.auth.controller.vo.GuildVO;
//import cn.catguild.auth.domain.Guild;
//import cn.catguild.auth.domain.type.GuildId;
//import cn.catguild.auth.repository.domain.GuildDO;
//import cn.catguild.auth.service.GuildService;
//import cn.catguild.common.api.ApiPage;
//import cn.catguild.common.api.ApiResponse;
//import cn.catguild.common.utility.IPageUtils;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 公会资源控制器
// *
// * @author xiyan
// * @date 2023/5/13 11:47
// */
//@Slf4j
//@AllArgsConstructor
//@RequestMapping("/guilds")
//@RestController
//public class GuildController {
//
//    private GuildService guildService;
//
//    @GetMapping("")
//    public ApiResponse<ApiPage<GuildVO>> page(@ModelAttribute  GuildCommand guildCommand){
//        ApiPage<Guild> page = guildService.getPage(guildCommand);
//        ApiPage<GuildVO> apiPage = IPageUtils.toApiPage(
//                page,
//                (p) -> {
//                    // 执行自定义的类型转换逻辑
//                    List<GuildVO> convertedList = new ArrayList<>();
//                    for (Object record : p.getRecords()) {
//                        // 执行类型转换
//                        convertedList.add(toVO((Guild) record));
//                    }
//                    return convertedList;
//                });
//        return ApiResponse.ok(apiPage);
//    }
//
//    @GetMapping("/{id}")
//    public ApiResponse<Guild> detail(@PathVariable("id") Long id){
//        Guild guild = guildService.get(new GuildId(id));
//        return ApiResponse.ok(guild);
//    }
//
//    @PostMapping("")
//    public ApiResponse<Void> add(@RequestBody Guild guild){
//        guildService.add(guild);
//        return ApiResponse.ok();
//    }
//
//    @PutMapping("/{id}")
//    public ApiResponse<Void> put(@PathVariable("id") Long id, @RequestBody Guild guild){
//        guildService.update(guild);
//        return ApiResponse.ok();
//    }
//
//    @PatchMapping("/{id}/status")
//    public ApiResponse<Void> patch(@PathVariable("id") Long id, @RequestBody GuildCommand guildCommand){
//        guildService.updateByStatus(id,guildCommand);
//        return ApiResponse.ok();
//    }
//
//    private GuildVO toVO(Guild guild) {
//        GuildVO guildVO = new GuildVO();
//        BeanUtils.copyProperties(guild, guildVO);
//        guildVO.setId(guild.getId().getValue());
//        return guildVO;
//    }
//}
