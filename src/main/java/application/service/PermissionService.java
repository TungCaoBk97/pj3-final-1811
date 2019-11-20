package application.service;

import application.model.*;
import application.repository.RolePermissionRepository;
import application.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private EntityManager entityManager;

    private void insertRolePermisison(long roleId, long permissionId) {
        RolePermission rolePermission = new RolePermission();

        Role role = entityManager.getReference(Role.class, roleId);
        Permission permission = entityManager.getReference(Permission.class, permissionId);

        rolePermission.setRole(role);
        rolePermission.setPermission(permission);

        rolePermissionRepository.save(rolePermission);
    }

    public void updatePermissions(long roleId, List<Long> newPermissionIds) {
        List<Long> currentPermissionIds = rolePermissionRepository.getAllPermissionByRoleId(roleId);

        List<Long> willInsert = new ArrayList<>(newPermissionIds);
        willInsert.removeAll(currentPermissionIds);

        List<Long> willDelete = new ArrayList<>(currentPermissionIds);
        willDelete.removeAll(newPermissionIds);

        System.out.println(willInsert);
        System.out.println(willDelete);

        for (Long permissionId: willInsert) {
            insertRolePermisison(roleId, permissionId);
        }

        rolePermissionRepository.deleteRolePermissions(roleId, willDelete);

        System.out.println(willInsert);
        System.out.println(willDelete);
    }

    private void insertUserRole(long userId, long roleId) {
        UserRole userRole = new UserRole();
        User user = entityManager.getReference(User.class, userId);
        Role role = entityManager.getReference(Role.class, roleId);

        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
    }

    public void updateRoles(long userId, List<Long> newRolesId) {
        List<Long> currentRoleId = userRoleRepository.getAllByUserId(userId);
        List<Long> willInsert = new ArrayList<>(newRolesId);
        willInsert.removeAll(currentRoleId);

        List<Long> willDelete = new ArrayList<>(currentRoleId);
        willDelete.removeAll(newRolesId);

        System.out.println(willInsert);
        System.out.println(willDelete);

        for (Long roleId: willInsert) {
            insertUserRole(userId, roleId);
        }

        userRoleRepository.deleteUserRoles(userId, willDelete);

        System.out.println(willInsert);
        System.out.println(willDelete);
    }
}
