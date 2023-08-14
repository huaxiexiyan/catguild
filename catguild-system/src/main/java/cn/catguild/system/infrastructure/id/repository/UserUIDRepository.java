package cn.catguild.system.infrastructure.id.repository;

import cn.catguild.system.infrastructure.id.domain.UserUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author xiyan
 * @date 2023/8/11 11:53
 */
@Repository
public interface UserUIDRepository extends JpaRepository<UserUID,Long> {

    Integer countByDeTimeIsNull();

    UserUID findTopByOrderByUidDesc();

    void removeByUid(Integer uid);

    @Query("select suu from UserUID suu where suu.cTime is null order by suu.cTime limit 100")
    Optional<List<UserUID>> findNextFixedUid();

}
