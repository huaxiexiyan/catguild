//package cn.catguild.auth.infrastructure;
//
//import cn.catguild.auth.domain.Resource;
//import cn.catguild.auth.domain.type.ResourceType;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
///**
// * @author xiyan
// * @date 2023/7/31 17:51
// */
//@Repository
//public interface ResourceRepository extends JpaRepository<Resource, Long> {
//
//    /**
//     * 获取app中目标角色拥有的资源集合
//     *
//     * @param tenantId
//     * @param roleIds
//     * @param appVersionId
//     * @return
//     */
//    @Query("select r from Resource r join AppResource ap on r.id = ap.resourceId and ap.appVersionId = :appVersionId " +
//            "join RoleResource rr on r.id = rr.resourceId and rr.tenantId = :tenantId " +
//            "where r.type = :type and rr.roleId in (:roleIds)")
//    List<Resource> findByTypeAndRoleIdsInAppVersionId(@Param("tenantId") Long tenantId,
//                                                      @Param("type") ResourceType type,
//                                                      @Param("roleIds") List<Long> roleIds,
//                                                      @Param("appVersionId") Long appVersionId);
//
//}
