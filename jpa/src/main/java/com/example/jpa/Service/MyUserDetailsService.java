package com.example.jpa.Service;

import com.example.jpa.Model.UserPrincipal;
import com.example.jpa.Model.Userss;
import com.example.jpa.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     Userss user = repo.getByUsername(username);

     if (user==null)
         throw new UsernameNotFoundException("no users");
         else
             return new UserPrincipal(user);
    }
}
