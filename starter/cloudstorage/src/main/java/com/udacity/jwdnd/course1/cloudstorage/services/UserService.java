package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private UserMapper userMapper;
    private HashService hashService;
    private Base64 Base64;

    public UserService(UserMapper usermapper, HashService hashservice) {
        this.userMapper = usermapper;
        this.hashService = hashservice;
    }

    public Boolean userExists(String username){
        User user = userMapper.getUser(username);
        if(user !=null){
            return true;
        }
        return false;
    }
    User getUserByName(String name){
        return  userMapper.getUser(name);
    }

   public Integer getUserIdByName(String name){
        return  userMapper.getUserID(name);
    }
    public int createUser(User user) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User( user.getUsername(), encodedSalt, hashedPassword, user.getFirstname(), user.getLastname()));
    }
}
