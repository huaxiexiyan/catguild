package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/7/31 15:29
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {



}
