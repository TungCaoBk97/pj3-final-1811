package application;

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

//        User accountingMember = new User();
//        accountingMember.setUsername("acc");
//        accountingMember.setPassword("acc");
//        userRepository.save(accountingMember);
//        System.out.println("Tao user acc successfully");
//
//        Role accountingRole = new Role();
//        accountingRole.setName("ACC");
//        roleRepository.save(accountingRole);
//        System.out.println("Tao role acc successfully");
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
