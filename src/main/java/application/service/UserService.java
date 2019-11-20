package application.service;

import application.model.Permission;
import application.model.Role;
import application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import application.model.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(long userId) {
        return userRepository.findById(userId);
    }

    public Role findRoleById(long roleId) {
        return roleRepository.findById(roleId);
    }

    public List<Permission> findAllPermissionsByUsername(String username) {
        return permissionRepository.findAllByUsername(username);
    }

    public List<Permission> findAllPermissionsByRoleId(long roleId) {
        return permissionRepository.findPermissionByRole(roleId);
    }

    public List<String> getAllPermission() {
        return permissionRepository.findAll().stream().map(r -> r.getName()).collect(Collectors.toList());
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public List<Role> getAllRolesByUser(User user){
        return userRoleRepository.getAllByUser(user);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public List<User> getAllUser() {
//        return userRepository.findAll().stream().map(r -> r.getUsername()).collect(Collectors.toList());
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPassword()));
        Date currentDate = new Date();
        user.setCreatedDate(currentDate);
        user.setLastUpdatedDate(currentDate);
        userRepository.save(user);
    }

    public List<Long> getAllPermissionByRoleId (long roleId){
        return rolePermissionRepository.getAllPermissionByRoleId(roleId);
    }

}
