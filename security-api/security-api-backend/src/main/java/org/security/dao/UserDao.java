package org.security.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author tibinatomy
 */
@Repository
public class UserDao {

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User(
                    "tbn@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMN"))
            ),
            new User(
                    "usr@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USR"))
            )
    );

    public UserDetails findUserByEmail(String email){
        UserDetails userDetails = APPLICATION_USERS // implementation of UserDetailsService loadByUsername method
                .stream()
                .filter(userDetail -> userDetail.getUsername().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User credentials are wrong !"));
        return userDetails;
    }
}
