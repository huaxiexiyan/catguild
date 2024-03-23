package cn.catguild.operation.application;

import cn.catguild.common.api.ApiPage;
import cn.catguild.operation.application.command.RolePageCommand;
import cn.catguild.operation.application.dto.RoleDTO;
import cn.catguild.operation.infrastructure.adapter.client.RoleClient;
import cn.catguild.operation.infrastructure.adapter.client.dto.RoleClientDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author xiyan
 * @date 2024/1/17 17:24
 */
@Slf4j
@AllArgsConstructor
@Component
public class RoleApplication {

	private final RoleClient roleClient;

	public ApiPage<RoleDTO> page(RolePageCommand rolePageCommand) {
		ApiPage<RoleClientDTO> rolePage = roleClient.page();
		ApiPage<RoleDTO> resultPage = new ApiPage<>();
		BeanUtils.copyProperties(rolePage,resultPage);
		return resultPage;
	}

}
