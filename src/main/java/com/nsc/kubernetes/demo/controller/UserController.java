package com.nsc.kubernetes.demo.controller;

import com.nsc.kubernetes.demo.model.AppUser;
import com.nsc.kubernetes.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(path = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AppUser register(@RequestBody AppUser appUser) {

        String encryptedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encryptedPassword);
        return userRepository.save(appUser);
    }

    /**
     * Roles (as they are used in many examples) are just "permissions" with a naming convention that says that
     * a role is a GrantedAuthority that starts with the prefix ROLE_
     */
    //@PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(path = "/get/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppUser register(@PathVariable String userId) {
        Optional<AppUser> appUserOptional = userRepository.findById(userId);
        return appUserOptional.get();
    }
}
