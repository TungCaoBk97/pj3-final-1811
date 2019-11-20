package application.repository;

import application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import application.model.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r inner join r.userRoles ur inner join ur.user u where u = ?1")
    List<Role> findAllByUser(User user);

    @Query("select r from Role r where r.id = ?1")
    Role findById(long roleId);

    Role findByName(String name);
}
