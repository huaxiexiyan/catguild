package cn.catguild.system.infrastructure.repository;

import cn.catguild.system.domain.Menu;
import cn.catguild.system.domain.repositroy.MenuRepository;
import cn.catguild.system.infrastructure.converter.MenuDataConverter;
import cn.catguild.system.infrastructure.domain.MenuDO;
import cn.catguild.system.infrastructure.domain.type.MenuType;
import cn.catguild.system.infrastructure.idgeneration.IdGenerationService;
import cn.catguild.system.infrastructure.repository.mapper.MenuDOMapper;
import cn.catguild.system.util.AuthUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2023/10/13 14:05
 */
@Slf4j
@AllArgsConstructor
@Component
public class MenuRepositoryImpl implements MenuRepository {

    private final MenuDOMapper baseMapper;

    private final MenuDataConverter dataConverter;

    private final IdGenerationService idService;

    @Override
    public void save(Menu menu) {
        Long loginId = AuthUtil.getLoginId();
        if (menu.getId() != null && menu.getId() > 0) {
            // update
            MenuDO menuDO = dataConverter.toData(menu);
            menuDO.setLmBy(loginId);
            menuDO.setLmTime(LocalDateTime.now());
            baseMapper.updateById(menuDO);
        } else {
            // insert
            MenuDO menuDO = dataConverter.toData(menu);
            menuDO.setId(idService.nextId());
            menuDO.setCBy(loginId);
            menuDO.setCTime(LocalDateTime.now());

            if (menuDO.getParentId() == null || menuDO.getParentId() == 0L) {
                menuDO.setParentId(0L);
                menuDO.setLevel(0);
            } else {
                MenuDO parentMenu = baseMapper.selectById(menuDO.getParentId());
                if (parentMenu != null) {
                    menuDO.setLevel(parentMenu.getLevel() + 1);
                }
            }
            baseMapper.insert(menuDO);
            menu.setId(dataConverter.fromData(menuDO).getId());
        }
    }

    @Override
    public List<Menu> findAll() {
        List<MenuDO> all = baseMapper.selectList(Wrappers.emptyWrapper());
        return all.stream()
                .map(dataConverter::fromData)
                .collect(Collectors.toList());
    }

    @Override
    public Menu findById(Long id) {
        MenuDO menuDO = baseMapper.selectById(id);
        if (menuDO == null) {
            return null;
        }
        Menu menu = dataConverter.fromData(menuDO);
        if (menu.hasParentMenu()) {
            MenuDO parentMenu = baseMapper.selectById(menu.getParentId());
            menu.setParentMenu(dataConverter.fromData(parentMenu));
        }
        return menu;
    }

    @Override
    public List<Menu> findByIds(List<Long> ids, MenuType menuType) {
        List<MenuDO> menuDOS = baseMapper.selectList(Wrappers.<MenuDO>lambdaQuery()
                .in(MenuDO::getId, ids)
                .eq(MenuDO::getType, menuType));
        return menuDOS.stream()
                .map(dataConverter::fromData)
                .collect(Collectors.toList());
    }

    @Override
    public List<Menu> findByIds(List<Long> ids) {
        List<MenuDO> menuDOS = baseMapper.selectList(Wrappers.<MenuDO>lambdaQuery()
                .in(MenuDO::getId, ids));
        return menuDOS.stream()
                .map(dataConverter::fromData)
                .collect(Collectors.toList());
    }

}
