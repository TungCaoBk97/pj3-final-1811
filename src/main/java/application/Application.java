package application;

import application.model.*;
import application.repository.*;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements ApplicationRunner {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        User admin = userService.findByUsername("admin");
//        Role ADMIN = roleRepository.findByName("ADMIN");
//        Permission addUserRole = permissionRepository.findByName("ADD_USER_ROLE");
//        Permission addRolePermission = permissionRepository.findByName("ADD_PERMISSION_ROLE");
//
//        rolePermissionRepository.save(new RolePermission(ADMIN, addUserRole));
//        rolePermissionRepository.save(new RolePermission(ADMIN, addRolePermission));


//        create new user
//        User hrMem = new User();
//        hrMem.setUsername("hr");
//        hrMem.setPassword("hr");
//        userService.saveUser(hrMem);
//        System.out.println("Tao user hr successfully");

//
//        create role
//        Role invRole = new Role();
//        invRole.setName("INV");
//        roleRepository.save(invRole);
//        System.out.println("Tao role inv successfully");


//
//
//        UserRole accUserRole = new UserRole();
//        accUserRole.setUser(accountingMember);
//        accUserRole.setRole(accountingRole);
//        userRoleRepository.save(accUserRole);
//        System.out.println("Tao userrole acc successfully");

//
//        UserRole adminUserRole = new UserRole();
//        adminUserRole.setRole(adminRole);
//        adminUserRole.setUser(admin);
//        userRoleRepository.save(adminUserRole);
    }
}
