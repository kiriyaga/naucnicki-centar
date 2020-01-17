package root.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import root.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM USER WHERE USER.USERNAME =?1 ", nativeQuery = true)
    User findByUsername(String username);
}
