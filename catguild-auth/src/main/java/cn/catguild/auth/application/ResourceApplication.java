package cn.catguild.auth.application;

import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.domain.repository.ResourceRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.presentation.model.ResourceQuery;
import cn.catguild.common.api.ApiPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/7/31 17:48
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class ResourceApplication {

    private final IdGenerationClient idClient;

    private final ResourceRepository resourceRepository;


    /**
     * 获取应用列表
     *
     * @return
     */
    public ApiPage<Resource> pageResource(ResourceQuery query) {
        //Sort sort = Sort.by(Sort.Direction.ASC, "id");
        //Pageable pageable = PageRequest.of((int) query.getCurrent() - 1, (int) query.getSize(), sort);
        //Page<Resource> appPage = resourceRepository.findAll(pageable);
        //List<Resource> records = appPage.getContent();
        // 填充版本列表

        ApiPage<Resource> apiPage = new ApiPage<>();
        //apiPage.setCurrent(appPage.getNumber() +1);
        //apiPage.setSize(appPage.getSize());
        //apiPage.setTotal(appPage.getTotalElements());
        //apiPage.setRecords(records);
        return apiPage;
    }


    public Resource getResource(Long id) {
        //Resource Resource = resourceRepository.findById(id).orElse(null);
        return null;
    }

    public Long addResource(Long tenantId, Resource resource) {
        resourceRepository.save(tenantId, resource);
        return resource.getId();
    }

    public void updateResource(Long id, Resource Resource) {
        Resource.setId(id);
        //Resource.setLmBy(AuthUtil.getLoginId());
        //Resource.setLmTime(LocalDateTime.now());
        //resourceRepository.saveAndFlush(Resource);
    }

    public void switchActiveStatus(Long tenantId, Long id) {
        Resource resource = resourceRepository.findById(tenantId, id);
        resource.switchActiveStatus();
        resourceRepository.save(tenantId, resource);
    }

    public void switchActiveStatusActive(Long tenantId, Long id) {
        Resource resource = resourceRepository.findById(tenantId, id);
        resource.switchActiveStatusActive();
        resourceRepository.save(tenantId, resource);
    }

    public void switchActiveStatusInactive(Long tenantId, Long id) {
        Resource resource = resourceRepository.findById(tenantId, id);
        resource.switchActiveStatusInactive();
        resourceRepository.save(tenantId, resource);
    }

    public List<Resource> listResource(Long tenantId, ResourceQuery resourceQuery) {
        return resourceRepository.listResource(tenantId, resourceQuery);
    }

}
