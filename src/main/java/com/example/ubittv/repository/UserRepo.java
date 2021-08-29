package com.example.ubittv.repository;

import com.example.ubittv.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long>{
    UserEntity findByUsername(String username);
}