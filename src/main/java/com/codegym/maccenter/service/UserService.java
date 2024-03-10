package com.codegym.maccenter.service;

import com.codegym.maccenter.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> getAllUser();
    void updateUser(User user);
    void saveUser(User user);
    void deleteUserById(int id);
    Optional<User> getUserById(int id);
    Optional<User> getUserByEmail(String email);

//    public boolean isEmailAlreadyInUse(String email);
}
