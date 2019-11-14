package application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

class SavePermissionsBody {
    private int roleId;

    private List<Integer> permissionIds;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}

@RestController
public class UserRestController {

    @PostMapping("/save-permissions")
    public String savePermissions(@RequestBody SavePermissionsBody body) {
        System.out.println(body.getRoleId());
        System.out.println(body.getPermissionIds());
        return "OK";
    }
}
