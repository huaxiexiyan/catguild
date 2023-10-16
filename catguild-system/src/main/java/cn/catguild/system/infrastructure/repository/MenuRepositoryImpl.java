package cn.catguild.system.infrastructure.repository;

import cn.catguild.system.domain.Menu;
import cn.catguild.system.domain.repositroy.MenuRepository;
import cn.catguild.system.infrastructure.converter.MenuDataConverter;
import cn.catguild.system.infrastructure.domain.MenuDO;
import cn.catguild.system.infrastructure.idgeneration.IdGenerationService;
import cn.catguild.system.infrastructure.repository.jpa.MenuDORepository;
import cn.catguild.system.infrastructure.repository.mapper.MenuDOMapper;
import cn.catguild.system.util.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2023/10/13 14:05
 */
@Slf4j
@AllArgsConstructor
@Component
public class MenuRepositoryImpl implements MenuRepository {

    private final MenuDORepository menuDORepository;

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
                menuDORepository.findById(menuDO.getParentId())
                        .ifPresent(parentMenu -> {
                            menuDO.setLevel(parentMenu.getLevel() + 1);
                        });
            }
            menuDORepository.saveAndFlush(menuDO);
            menu.setId(dataConverter.fromData(menuDO).getId());
        }
    }

    @Override
    public List<Menu> findAll() {
        List<MenuDO> all = menuDORepository.findAll();
        return all.stream()
                .map(dataConverter::fromData)
                .collect(Collectors.toList());
    }

    @Override
    public Menu findById(Long id) {
        Optional<MenuDO> menuOptional = menuDORepository.findById(id);
        MenuDO menuDO = menuOptional.orElseGet(null);
        if (menuDO == null){
            return null;
        }
        Menu menu = dataConverter.fromData(menuDO);
        if (menuDO.getParentId() != null){
            Optional<MenuDO> parentMenu = menuDORepository.findById(menuDO.getParentId());
            parentMenu.ifPresent(m-> menu.setParentMenu(dataConverter.fromData(m)));
        }
        return menu;
    }

}
