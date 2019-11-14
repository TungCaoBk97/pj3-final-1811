package application;

import application.model.Permission;
import application.model.Role;
import application.model.User;
import application.model.UserRole;
import application.repository.RoleRepository;
import application.repository.UserRepository;
import application.repository.UserRoleRepository;
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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(roleRepository.findAll().stream().map(r -> r.getName()).collect(Collectors.toList()));
        System.out.println(userService.getAllPermission());
        System.out.println(userService.getAllUser());

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
