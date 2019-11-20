package application.repository;

import application.model.Role;
import application.model.User;
import application.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select ur.role.id from UserRole ur where ur.user.id = ?1")
    List<Long> getAllByUserId(long userId);

    @Query("select ur.role from UserRole ur where ur.user = ?1")
    List<Role> getAllByUser(User user);

    @Transactional
    @Modifying
    @Query("delete from UserRole ur where ur.user.id = ?1 and ur.role.id in ?2")
    void deleteUserRoles(long userId, List<Long> roleId);
}
