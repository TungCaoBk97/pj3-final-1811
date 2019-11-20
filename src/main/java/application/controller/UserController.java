package application.controller;

import application.model.Permission;
import application.model.Role;
import application.service.PermissionService;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import application.model.User;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private User userSelected = new User();
    private Role roleSelected = new Role();

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @GetMapping("/register")
    public ModelAndView signUp(){
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("register");
        return model;
    }

    @PostMapping("/register")
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        User userExist = userService.findByUsername(user.getUsername());

        if (userExist != null){
            bindingResult.rejectValue("username", "error.user", "This user already exist!");
        }
        if (bindingResult.hasErrors()){
            model.setViewName("register");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been register successfully!");
            model.addObject("user", new User());
            model.setViewName("register");
        }
        return model;
    }

    @GetMapping("/")
    public ModelAndView home(Principal principal){
        ModelAndView model = new ModelAndView();

        List<Permission> permissions = userService.findAllPermissionsByUsername(principal.getName());
        model.addObject("allowedTabs", AllowedTabs.fromPermissions(permissions));
        model.addObject("userName", principal.getName());
        model.setViewName("index");
        return model;
    }

    @GetMapping("/add-role")
    public ModelAndView addRole(Principal principal){
        ModelAndView model = new ModelAndView();
        List<User> userList = userService.getAllUser();
        List<Permission> permissions = userService.findAllPermissionsByUsername(principal.getName());
        model.addObject("allowedTabs", AllowedTabs.fromPermissions(permissions));

        model.addObject("allUsers", userService.getAllUser());

        model.addObject("userName", principal.getName());
        model.setViewName("add-role");
        return model;
    }

    @GetMapping("/add-permission")
    public ModelAndView addPermission(Principal principal){
        ModelAndView model = new ModelAndView();
        List<Permission> permissions = userService.findAllPermissionsByUsername(principal.getName());
        model.addObject("allowedTabs", AllowedTabs.fromPermissions(permissions));

        model.addObject("allRoles", userService.getAllRoles());

        model.addObject("userName", principal.getName());
        model.setViewName("add-permission");
        return model;
    }

    @GetMapping("/add-user-role/{id}")
    public ModelAndView addUserROle(Principal principal, @PathVariable("id") long userId){
        ModelAndView model = new ModelAndView();
        userSelected = userService.findUserById(userId);
        List<Role> roleList = userService.getAllRoles();
        List<Role> roleListBySelectedUser = userService.getAllRolesByUser(userSelected);

        //get permission for principal
        List<Permission> permissions = userService.findAllPermissionsByUsername(principal.getName());
        model.addObject("allowedTabs", AllowedTabs.fromPermissions(permissions));

        model.addObject("selectedUser", userSelected);
        model.addObject("roleListBySelectedUser", roleListBySelectedUser);
        model.addObject("allRoles", roleList);
        model.addObject("userName", principal.getName());
        model.setViewName("add-user-role");
        return model;
    }


    @GetMapping("/add-role-permission/{id}")
    public ModelAndView addPermissionRole(Principal principal, @PathVariable("id") long roleId){
        ModelAndView model = new ModelAndView();
        roleSelected = userService.findRoleById(roleId);
        List<Permission> listPermissionByRoleId = userService.findAllPermissionsByRoleId(roleSelected.getId());

        List<Permission> permissions = userService.findAllPermissionsByUsername(principal.getName());
        model.addObject("allowedTabs", AllowedTabs.fromPermissions(permissions));

        model.addObject("allPermissions", userService.getAllPermissions());
        model.addObject("listPermissionByRoleId", listPermissionByRoleId);
        model.addObject("userName", principal.getName());
        model.addObject("roleSelected", roleSelected);
        model.setViewName("add-role-permission");
        return model;
    }

    @GetMapping("/404")
    public ModelAndView accessDenied(){
        ModelAndView model = new ModelAndView();
        model.setViewName("404");
        return model;
    }

}
