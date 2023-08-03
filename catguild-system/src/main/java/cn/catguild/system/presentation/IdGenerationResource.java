package cn.catguild.system.presentation;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.system.infrastructure.id.IdGenerationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.annotation.PostConstruct;

/**
 *
 * @author xiyan
 * @date 2023/8/1 15:05
 */
@AllArgsConstructor
@RequestMapping("/ids")
@Slf4j
@RestController
public class IdGenerationResource {

    private IdGenerationService idGenerationService;

    @GetMapping("/next")
    public ApiResponse<Long> nextId() {
        // 调用分布式ID生成服务的RPC接口，获取唯一ID
        // 假设生成的ID是一个字符串类型
        String uniqueId = idGenerationService.next();
        return ApiResponse.ok(Long.parseLong(uniqueId));
    }

}
