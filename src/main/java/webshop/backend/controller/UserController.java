package webshop.backend.controller;

import webshop.backend.model.User;
import webshop.backend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.OK)
    public void register(@RequestBody User user) throws Exception {
        userService.register(user);
    }

    @GetMapping("/loginInfo")
    public User info(Authentication auth) {
        return userService.getLoginInfo(auth);
    }
}
