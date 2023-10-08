package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.CatRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/7/31 15:29
 */
@Repository
public interface RoleRepository extends JpaRepository<CatRole, Long> {

    @Query("select cr from CatRole cr join UserRole ur on cr.id = ur.roleId and ur.tenantId = :tenantId " +
            "where ur.userId = :userId and cr.tenantId = :tenantId")
    List<CatRole> findByUserId(@Param("tenantId") Long tenantId, @Param("userId") Long userId);

}
