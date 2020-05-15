package webshop.backend.service;

import webshop.backend.model.User;
import webshop.backend.repo.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void register(User user) throws Exception {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        return optionalUser.orElseThrow(() -> new UsernameNotFoundException("no user with name " + username + "found"));
    }

    public User getLoginInfo(Authentication auth) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(auth.getName());
        user.orElseThrow(() -> new UsernameNotFoundException("no user with name '" + auth.getName() + "'"));
        return user.get();
    }
}
