package cn.catguild.auth.application;

import cn.catguild.auth.domain.App;
import cn.catguild.auth.domain.AppVersion;
import cn.catguild.auth.infrastructure.AppRepository;
import cn.catguild.auth.infrastructure.AppVersionRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.common.type.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class AppApplicationService {

    private final IdGenerationClient idClient;

    private final AppRepository appRepository;

    private final AppVersionRepository appVersionRepository;

    /**
     * 获取应用列表
     *
     * @return
     */
    public List<App> listApp() {
        return appRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public App getApp(Long id) {
        App app = appRepository.findById(id).orElse(null);
        return app;
    }

    public void addApp(App app) {
        app.setId(idClient.nextId());
        app.setStatus(ActiveStatus.ACTIVE);
        app.setCBy(AuthUtil.getLoginId());
        app.setCTime(LocalDateTime.now());
        appRepository.saveAndFlush(app);
    }

    public void updateApp(Long id, App app) {
        app.setId(id);
        app.setLmBy(AuthUtil.getLoginId());
        app.setLmTime(LocalDateTime.now());
        appRepository.saveAndFlush(app);
    }

    public void updateAppStatus(Long id, ActiveStatus status) {
        appRepository.findById(id).ifPresent(app -> {
            app.setStatus(status);
            app.setLmBy(AuthUtil.getLoginId());
            app.setLmTime(LocalDateTime.now());
            appRepository.saveAndFlush(app);
        });
    }

    /**
     * 查询应用下的所有版本
     *
     * @param id
     * @return
     */
    public List<AppVersion> listAppVersion(Long id) {
        return appVersionRepository.findByAppId(id);
    }

    /**
     *
     * @param id 应用id
     * @param appVersion
     */
    public void addAppVersion(Long id, AppVersion appVersion) {
        appVersion.setId(idClient.nextId());
        appVersion.setAppId(id);
        appVersion.setUid(idClient.nextUid());
        appVersion.setStatus(ActiveStatus.ACTIVE);
        appVersion.setCBy(AuthUtil.getLoginId());
        appVersion.setCTime(LocalDateTime.now());
        appVersionRepository.saveAndFlush(appVersion);
    }

    public void updateAppVersion(Long id, Long versionId, AppVersion appVersion) {
        appVersion.setId(versionId);
        appVersion.setAppId(id);
        appVersion.setLmBy(AuthUtil.getLoginId());
        appVersion.setLmTime(LocalDateTime.now());
        appVersionRepository.saveAndFlush(appVersion);
    }

    public void updateAppVersionStatus(Long id, Long versionId, ActiveStatus status) {
        appVersionRepository.findById(versionId).ifPresent(appVersion -> {
            appVersion.setStatus(status);
            appVersion.setLmBy(AuthUtil.getLoginId());
            appVersion.setLmTime(LocalDateTime.now());
            appVersionRepository.saveAndFlush(appVersion);
        });
    }

}
