package application.service;

import application.model.Permission;
import application.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import application.model.User;
import application.repository.RoleRepository;
import application.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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

    public List<Permission> findAllPermissionsByUsername(String username) {
        return permissionRepository.findAllByUsername(username);
    }

    public List<String> getAllPermission() {
        return permissionRepository.findAll().stream().map(r -> r.getName()).collect(Collectors.toList());
    }

    public List<Permission> getAllPermissions() {
//        return userRepository.findAll().stream().map(r -> r.getUsername()).collect(Collectors.toList());
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
}
