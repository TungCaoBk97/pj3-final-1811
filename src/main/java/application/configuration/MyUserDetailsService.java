package application.configuration;

import application.model.Permission;
import application.model.User;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        List<Permission> permissions = userService.findAllPermissionsByUsername(username);
        List<GrantedAuthority> grantedAuthorities =
                permissions.stream()
                        .map(permission -> new MyGrantedAuthority(permission.getName()))
                        .collect(Collectors.toList());
        return new UserPrincipal(user, grantedAuthorities);
    }
}
