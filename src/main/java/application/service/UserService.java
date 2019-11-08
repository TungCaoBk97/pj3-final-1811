package application.service;

import application.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import application.model.Role;
import application.model.User;
import application.repository.RoleRepository;
import application.repository.UserRepository;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPassword()));
        Date currentDate = new Date();
        user.setCreatedDate(currentDate);
        user.setLastUpdatedDate(currentDate);
        userRepository.save(user);
    }
}
