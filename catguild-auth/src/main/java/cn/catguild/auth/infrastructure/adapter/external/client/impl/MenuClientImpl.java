package cn.catguild.auth.infrastructure.adapter.external.client.impl;

import cn.catguild.auth.infrastructure.adapter.external.client.MenuClient;
import cn.catguild.system.api.dto.MenuDTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/31 15:21
 */
@Component
public class MenuClientImpl implements MenuClient {

    @Override
    public List<MenuDTO> listMenuByAppIds(List<Long> appIds) {
        return null;
    }

}
