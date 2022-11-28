package com.example.RestAPICrudAppSeriesEvents.service;

import com.example.RestAPICrudAppSeriesEvents.model.User;
import com.example.RestAPICrudAppSeriesEvents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }
    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ExpressionException("User not found with id = " + id));
    }
    public void deleteUserById(Long id)
    {
        userRepository.deleteById(id);
    }
    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }
}


