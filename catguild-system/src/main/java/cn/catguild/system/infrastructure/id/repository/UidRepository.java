package cn.catguild.system.infrastructure.id.repository;

import cn.catguild.system.infrastructure.id.domain.Uid;
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
public interface UidRepository extends JpaRepository<Uid,Long> {

    Integer countByDeTimeIsNull();

    Uid findTopByOrderByUidDesc();

    Optional<Uid> findByUid(Integer uid);

    void removeByUid(Integer uid);

    @Query("select suu from Uid suu where suu.deTime is null order by suu.cTime limit 100")
    Optional<List<Uid>> findNextFixedUid();

}
