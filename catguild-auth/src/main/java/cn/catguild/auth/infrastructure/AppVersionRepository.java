package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/7/31 17:51
 */
@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion,Long> {

    @Query("select a from AppVersion a where a.appId = :appId order by a.id asc")
    List<AppVersion> findByAppId(@Param("appId") Long appId);

}
