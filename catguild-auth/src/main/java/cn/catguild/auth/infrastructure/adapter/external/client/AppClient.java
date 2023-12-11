package cn.catguild.auth.infrastructure.adapter.external.client;

import cn.catguild.system.api.dto.AppDTO;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/3 16:05
 */
public interface AppClient {

    List<AppDTO> listApp(List<Long> appIds);

}
