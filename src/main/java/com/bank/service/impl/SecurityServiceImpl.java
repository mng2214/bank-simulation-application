package com.bank.service.impl;

import com.bank.entity.User;
import com.bank.entity.common.UserPrincipal;
import com.bank.repository.UserRepository;
import com.bank.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            return new UserPrincipal(userRepository.findByUsername(username));
//        } catch (UsernameNotFoundException e) {
//            throw new UsernameNotFoundException("User not found or credentials invalid");
//        }


//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new UserPrincipal(user);

        User byUsername = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserPrincipal(byUsername);
    }

}
