package com.sapient.ecom.dao;

import com.sapient.ecom.model.UserT;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<UserT, Long> {
    UserT findByUsername(String username);
}
