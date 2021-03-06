package com.example.demo.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

//@Transactional(readOnly=true)
@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser>findByEmail(String email);
}
