package application.configuration;

import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {
    private final String name;

    public MyGrantedAuthority(String name) {
        this.name = name;
    }
    @Override
    public String getAuthority() {
        return this.name;
    }
}
