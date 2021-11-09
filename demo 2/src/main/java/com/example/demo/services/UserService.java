package com.example.demo.services;


import com.example.demo.entity.User;
import com.example.demo.helpers.ValidateHelper;
import com.example.demo.models.UserRequest;
import com.example.demo.respository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public boolean saveUser(UserRequest userRequest) {
    if(!ValidateHelper.validate(userRequest.getEmail())) {
      return false;
    }
    User byEmail = userRepository.findByEmail(userRequest.getEmail());
    if(byEmail != null) {
      return false;
    }
    User user = new User(userRequest.getPassword(), userRequest.getFirstName(), userRequest.getSecondName(),
        userRequest.getEmail());
    userRepository.save(user);
    return true;
  }

  public void removeUser(Integer id) {
    boolean exists = userRepository.existsById(id.longValue());
    if(!exists) {
        throw new IllegalStateException("Student with id " + id + "doesn't exist");
    }
    userRepository.deleteById(id.longValue());
  }

  public void updateUser(User user) {

      if(!user.getEmail().equals(userRepository.getById(user.getId()).getEmail())){
        throw new IllegalStateException("You cannot change user email.");
      }
      User user1 = userRepository.getById(user.getId());
      user1.update(user.getPassword(), user.getFirstName(),user.getSecondName());
      userRepository.save(user1);
  }

  public User getUser(Integer id) {
    return userRepository.findById(id.longValue()).orElse(null);
  }
}