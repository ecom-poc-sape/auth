package com.sapient.ecom.service;

import com.sapient.ecom.model.UserT;
import com.sapient.ecom.model.UserDto;

import java.util.List;

public interface UserService {

    UserT save(UserDto user);
    List<UserT> findAll();
    void delete(long id);
    UserT findOne(String username);

    UserT findById(Long id);
}
