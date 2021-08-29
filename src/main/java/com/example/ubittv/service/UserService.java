package com.example.ubittv.service;


import com.example.ubittv.entity.UserEntity;
import com.example.ubittv.exception.UserAlreadyExistException;
import com.example.ubittv.exception.UserNotFoundException;
import com.example.ubittv.model.User;
import com.example.ubittv.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if(userRepo.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        return userRepo.save(user);
    }

    public User getOneUser(Long id) throws UserNotFoundException{
        UserEntity user = userRepo.findById(id).get();
        if(user == null) {
            throw new UserNotFoundException("User has been not found");
        }
        return User.toModel(user);
    }

    public Long deleteUser(Long id) {
        userRepo.deleteById(id);
        return id;
    }
}
