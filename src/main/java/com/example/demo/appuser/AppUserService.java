package com.example.demo.appuser;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private final static String NOT_FOUND = "USER with email %s NOT FOUND";

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException(String.format(NOT_FOUND,email)));
    }
}
