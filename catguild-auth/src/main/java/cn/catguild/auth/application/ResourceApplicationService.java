package cn.catguild.auth.application;

import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.infrastructure.ResourceRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.auth.presentation.model.ResourceQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/7/31 17:48
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class ResourceApplicationService {

    private final IdGenerationClient idClient;

    private final ResourceRepository resourceRepository;


    /**
     * 获取应用列表
     *
     * @return
     */
    public ApiPage<Resource> listResource(ResourceQuery query) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of((int) query.getCurrent() - 1, (int) query.getSize(), sort);
        Page<Resource> appPage = resourceRepository.findAll(pageable);
        List<Resource> records = appPage.getContent();
        // 填充版本列表

        ApiPage<Resource> apiPage = new ApiPage<>();
        apiPage.setCurrent(appPage.getNumber() +1);
        apiPage.setSize(appPage.getSize());
        apiPage.setTotal(appPage.getTotalElements());
        apiPage.setRecords(records);
        return apiPage;
    }



    public Resource getResource(Long id) {
        Resource Resource = resourceRepository.findById(id).orElse(null);
        return Resource;
    }

    public Long addResource(Resource Resource) {
        Long id = idClient.nextId();
        Resource.setId(id);
        Resource.setStatus(ActiveStatus.ACTIVE);
        Resource.setCBy(AuthUtil.getLoginId());
        Resource.setCTime(LocalDateTime.now());
        resourceRepository.saveAndFlush(Resource);
        return id;
    }

    public void updateResource(Long id, Resource Resource) {
        Resource.setId(id);
        Resource.setLmBy(AuthUtil.getLoginId());
        Resource.setLmTime(LocalDateTime.now());
        resourceRepository.saveAndFlush(Resource);
    }

    public void updateResourceStatus(Long id, ActiveStatus status) {
        resourceRepository.findById(id).ifPresent(Resource -> {
            Resource.setStatus(status);
            Resource.setLmBy(AuthUtil.getLoginId());
            Resource.setLmTime(LocalDateTime.now());
            resourceRepository.saveAndFlush(Resource);
        });
    }

}
