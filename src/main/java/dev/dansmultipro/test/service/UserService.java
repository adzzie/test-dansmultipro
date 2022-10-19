package dev.dansmultipro.test.service;

import dev.dansmultipro.test.dao.UserDao;
import dev.dansmultipro.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user =  userDao.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not Found!"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
    }

    public boolean isUserExists(User user){
        return userDao.findByEmail(user.getEmail()).isPresent()?true: false;
    }

    public User getUserByEmail(String email){
        return userDao.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found!"));
    }
    public User addUser(User user){
        if(userDao.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("User already Exists!");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userDao.save(user);
    }


}
