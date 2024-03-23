package cn.catguild.operation.presentation;

import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import cn.catguild.common.type.CatTreeNode;
import cn.catguild.common.utility.ApiPageUtils;
import cn.catguild.common.utility.JSONUtils;
import cn.catguild.operation.application.MenuApplication;
import cn.catguild.operation.application.command.MenuPageCommand;
import cn.catguild.operation.application.command.MenuTreeCommand;
import cn.catguild.operation.application.dto.MenuDTO;
import cn.catguild.operation.domain.Menu;
import cn.catguild.operation.presentation.request.MenuQueryRequest;
import cn.catguild.operation.presentation.vo.MenuPageVO;
import cn.catguild.operation.presentation.vo.MenuTreeVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiyan
 * @date 2024/1/8 15:35
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/menus")
@RestController
public class MenuResource {

	private MenuApplication menuApplication;

	@GetMapping("")
	public ApiResponse<ApiPage<MenuPageVO>> page(MenuQueryRequest menuQueryRequest) {
		MenuPageCommand menuPageCommand = new MenuPageCommand();
		BeanUtils.copyProperties(menuQueryRequest, menuPageCommand);
		ApiPage<MenuDTO> page = menuApplication.page(menuPageCommand);
		ApiPage<MenuPageVO> menuVOApiPage = ApiPageUtils.convertApiPage(page, (iPage) -> {
			List<MenuDTO> records = iPage.getRecords();
			return records.stream()
				.map(r -> {
					MenuPageVO menu = new MenuPageVO();
					BeanUtils.copyProperties(r, menu);
					return menu;
				}).toList();
		});
		return ApiResponse.ok(menuVOApiPage);
	}

	@GetMapping("/tree")
	public ApiResponse<List<MenuTreeVO>> tree(MenuQueryRequest menuQueryRequest) {
		MenuTreeCommand menuTreeCommand = new MenuTreeCommand();
		BeanUtils.copyProperties(menuQueryRequest, menuTreeCommand);
		List<MenuDTO> list = menuApplication.tree(menuTreeCommand);
		List<MenuTreeVO> result = CatTreeNode.copy(list,MenuTreeVO.class);
		return ApiResponse.ok(result);
	}

	@GetMapping("/auth")
	public ApiResponse<Map<String,Object>> authTree() {
		// List<Menu> tree = menuApplicationService.authTree();
		Map<String,Object> map = new HashMap<>();
		List<Menu> tree = getByJson();
		map.put("list",tree);
		return ApiResponse.ok(map);
	}

	private List<Menu> getByJson() {
		// String json = "[{\"path\":\"/list\",\"name\":\"list\",\"component\":\"LAYOUT\",\"redirect\":\"/list/base\",\"meta\":{\"title\":{\"zh_CN\":\"列表页\",\"en_US\":\"List\"},\"icon\":\"view-list\"},\"children\":[{\"path\":\"base\",\"name\":\"ListBase\",\"component\":\"/list/base/index\",\"meta\":{\"title\":{\"zh_CN\":\"基础列表页\",\"en_US\":\"Base List\"}}},{\"path\":\"card\",\"name\":\"ListCard\",\"component\":\"/list/card/index\",\"meta\":{\"title\":{\"zh_CN\":\"卡片列表页\",\"en_US\":\"Card List\"}}},{\"path\":\"filter\",\"name\":\"ListFilter\",\"component\":\"/list/filter/index\",\"meta\":{\"title\":{\"zh_CN\":\"筛选列表页\",\"en_US\":\"Filter List\"}}},{\"path\":\"tree\",\"name\":\"ListTree\",\"component\":\"/list/tree/index\",\"meta\":{\"title\":{\"zh_CN\":\"树状筛选列表页\",\"en_US\":\"Tree List\"}}}]},{\"path\":\"/form\",\"name\":\"form\",\"component\":\"LAYOUT\",\"redirect\":\"/form/base\",\"meta\":{\"title\":{\"zh_CN\":\"表单页\",\"en_US\":\"Form\"},\"icon\":\"edit-1\"},\"children\":[{\"path\":\"base\",\"name\":\"FormBase\",\"component\":\"/form/base/index\",\"meta\":{\"title\":{\"zh_CN\":\"基础表单页\",\"en_US\":\"Base Form\"}}},{\"path\":\"step\",\"name\":\"FormStep\",\"component\":\"/form/step/index\",\"meta\":{\"title\":{\"zh_CN\":\"分步表单页\",\"en_US\":\"Step Form\"}}}]},{\"path\":\"/detail\",\"name\":\"detail\",\"component\":\"LAYOUT\",\"redirect\":\"/detail/base\",\"meta\":{\"title\":{\"zh_CN\":\"详情页\",\"en_US\":\"Detail\"},\"icon\":\"layers\"},\"children\":[{\"path\":\"base\",\"name\":\"DetailBase\",\"component\":\"/detail/base/index\",\"meta\":{\"title\":{\"zh_CN\":\"基础详情页\",\"en_US\":\"Base Detail\"}}},{\"path\":\"advanced\",\"name\":\"DetailAdvanced\",\"component\":\"/detail/advanced/index\",\"meta\":{\"title\":{\"zh_CN\":\"多卡片详情页\",\"en_US\":\"Card Detail\"}}},{\"path\":\"deploy\",\"name\":\"DetailDeploy\",\"component\":\"/detail/deploy/index\",\"meta\":{\"title\":{\"zh_CN\":\"数据详情页\",\"en_US\":\"Data Detail\"}}},{\"path\":\"secondary\",\"name\":\"DetailSecondary\",\"component\":\"/detail/secondary/index\",\"meta\":{\"title\":{\"zh_CN\":\"二级详情页\",\"en_US\":\"Secondary Detail\"}}}]},{\"path\":\"/frame\",\"name\":\"Frame\",\"component\":\"Layout\",\"redirect\":\"/frame/doc\",\"meta\":{\"icon\":\"internet\",\"title\":{\"zh_CN\":\"外部页面\",\"en_US\":\"External\"}},\"children\":[{\"path\":\"doc\",\"name\":\"Doc\",\"component\":\"IFrame\",\"meta\":{\"frameSrc\":\"https://tdesign.tencent.com/starter/docs/vue-next/get-started\",\"title\":{\"zh_CN\":\"使用文档（内嵌）\",\"en_US\":\"Documentation(IFrame)\"}}},{\"path\":\"TDesign\",\"name\":\"TDesign\",\"component\":\"IFrame\",\"meta\":{\"frameSrc\":\"https://tdesign.tencent.com/vue-next/getting-started\",\"title\":{\"zh_CN\":\"TDesign 文档（内嵌）\",\"en_US\":\"TDesign (IFrame)\"}}},{\"path\":\"TDesign2\",\"name\":\"TDesign2\",\"component\":\"IFrame\",\"meta\":{\"frameSrc\":\"https://tdesign.tencent.com/vue-next/getting-started\",\"frameBlank\":true,\"title\":{\"zh_CN\":\"TDesign 文档（外链\",\"en_US\":\"TDesign Doc(Link)\"}}}]}]";
		String json = """
			[{
				"path": "/setting",
				"name": "Setting",
				"component": "LAYOUT",
				"redirect": "/setting/role",
				"meta": {
					"title": {
						"zh_CN": "基础设置",
						"en_US": "List"
					},
					"icon": "view-list"
				},
				"children": [{
					"path": "role",
					"name": "SettingRole",
					"component": "/setting/role/index",
					"meta": {
						"title": {
							"zh_CN": "角色管理",
							"en_US": "Role Setting"
						}
					}
				}, {
					"path": "menu",
					"name": "SettingMenu",
					"component": "/setting/menu/index",
					"meta": {
						"title": {
							"zh_CN": "菜单管理",
							"en_US": "Menu Setting"
						}
					}
				}]
			},
			{
				"path": "/list",
				"name": "list",
				"component": "LAYOUT",
				"redirect": "/list/base",
				"meta": {
					"title": {
						"zh_CN": "列表页",
						"en_US": "List"
					},
					"icon": "view-list"
				},
				"children": [{
					"path": "base",
					"name": "ListBase",
					"component": "/list/base/index",
					"meta": {
						"title": {
							"zh_CN": "基础列表页",
							"en_US": "Base List"
						}
					}
				}, {
					"path": "card",
					"name": "ListCard",
					"component": "/list/card/index",
					"meta": {
						"title": {
							"zh_CN": "卡片列表页",
							"en_US": "Card List"
						}
					}
				}, {
					"path": "filter",
					"name": "ListFilter",
					"component": "/list/filter/index",
					"meta": {
						"title": {
							"zh_CN": "筛选列表页",
							"en_US": "Filter List"
						}
					}
				}, {
					"path": "tree",
					"name": "ListTree",
					"component": "/list/tree/index",
					"meta": {
						"title": {
							"zh_CN": "树状筛选列表页",
							"en_US": "Tree List"
						}
					}
				}]
			}, {
				"path": "/form",
				"name": "form",
				"component": "LAYOUT",
				"redirect": "/form/base",
				"meta": {
					"title": {
						"zh_CN": "表单页",
						"en_US": "Form"
					},
					"icon": "edit-1"
				},
				"children": [{
					"path": "base",
					"name": "FormBase",
					"component": "/form/base/index",
					"meta": {
						"title": {
							"zh_CN": "基础表单页",
							"en_US": "Base Form"
						}
					}
				}, {
					"path": "step",
					"name": "FormStep",
					"component": "/form/step/index",
					"meta": {
						"title": {
							"zh_CN": "分步表单页",
							"en_US": "Step Form"
						}
					}
				}]
			}, {
				"path": "/detail",
				"name": "detail",
				"component": "LAYOUT",
				"redirect": "/detail/base",
				"meta": {
					"title": {
						"zh_CN": "详情页",
						"en_US": "Detail"
					},
					"icon": "layers"
				},
				"children": [{
					"path": "base",
					"name": "DetailBase",
					"component": "/detail/base/index",
					"meta": {
						"title": {
							"zh_CN": "基础详情页",
							"en_US": "Base Detail"
						}
					}
				}, {
					"path": "advanced",
					"name": "DetailAdvanced",
					"component": "/detail/advanced/index",
					"meta": {
						"title": {
							"zh_CN": "多卡片详情页",
							"en_US": "Card Detail"
						}
					}
				}, {
					"path": "deploy",
					"name": "DetailDeploy",
					"component": "/detail/deploy/index",
					"meta": {
						"title": {
							"zh_CN": "数据详情页",
							"en_US": "Data Detail"
						}
					}
				}, {
					"path": "secondary",
					"name": "DetailSecondary",
					"component": "/detail/secondary/index",
					"meta": {
						"title": {
							"zh_CN": "二级详情页",
							"en_US": "Secondary Detail"
						}
					}
				}]
			}, {
				"path": "/frame",
				"name": "Frame",
				"component": "Layout",
				"redirect": "/frame/doc",
				"meta": {
					"icon": "internet",
					"title": {
						"zh_CN": "外部页面",
						"en_US": "External"
					}
				},
				"children": [{
					"path": "doc",
					"name": "Doc",
					"component": "IFrame",
					"meta": {
						"frameSrc": "https://tdesign.tencent.com/starter/docs/vue-next/get-started",
						"title": {
							"zh_CN": "使用文档（内嵌）",
							"en_US": "Documentation(IFrame)"
						}
					}
				}, {
					"path": "TDesign",
					"name": "TDesign",
					"component": "IFrame",
					"meta": {
						"frameSrc": "https://tdesign.tencent.com/vue-next/getting-started",
						"title": {
							"zh_CN": "TDesign 文档（内嵌）",
							"en_US": "TDesign (IFrame)"
						}
					}
				}, {
					"path": "TDesign2",
					"name": "TDesign2",
					"component": "IFrame",
					"meta": {
						"frameSrc": "https://tdesign.tencent.com/vue-next/getting-started",
						"frameBlank": true,
						"title": {
							"zh_CN": "TDesign 文档（外链",
							"en_US": "TDesign Doc(Link)"
						}
					}
				}]
			}]
			""";
		List<Menu> menus = JSONUtils.parseArray(json, Menu.class);
		log.info("{}",menus);
		return menus;
	}


}

