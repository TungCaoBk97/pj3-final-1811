package application.controller;

import application.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

class SavePermissionsBody {
    public long roleId;

    public List<Long> permissionIds;
}

class SaveRolesBody {
    public long userId;

    public List<Long> roleIds;
}

@RestController
public class UserRestController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/add-role-permission")
    public String savePermissions(@RequestBody SavePermissionsBody body) {
        permissionService.updatePermissions(body.roleId, body.permissionIds);
        return "redirect:/add-role-permission/" + body.roleId;
    }

    @PostMapping("/add-user-role")
    public String saveRoles(@RequestBody SaveRolesBody body) {
        permissionService.updateRoles(body.userId, body.roleIds);
        return "redirect:/add-user-role/" + body.userId;
    }
}
