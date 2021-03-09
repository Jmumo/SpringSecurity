package com.example.demo.appuser;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private final static String NOT_FOUND = "USER with email %s NOT FOUND";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return appUserRepository.findByEmail(email).
                orElseThrow(()->new UsernameNotFoundException(String.format(NOT_FOUND,email)));
    }

    public String signUpUser(AppUser appUser){
       boolean userExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

       if(userExist){
           throw new IllegalStateException("email already  taken");
       }
       String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

       appUser.setPassword(encodedPassword);

       appUserRepository.save(appUser);
        return  "";
    }
}
