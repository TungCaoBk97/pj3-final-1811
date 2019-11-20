package application.repository;

import application.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    @Query("select rp.permission.id from RolePermission rp  where rp.role.id = ?1")
    List<Long> getAllPermissionByRoleId(long roleId);

    @Transactional
    @Modifying
    @Query("delete from RolePermission rp where rp.role.id = ?1 and rp.permission.id in ?2")
    void deleteRolePermissions(long roleId, List<Long> permissionId);
}
