package com.sapient.ecom.service.impl;

import com.sapient.ecom.dao.UserDao;
import com.sapient.ecom.model.UserT;
import com.sapient.ecom.service.UserService;
import com.sapient.ecom.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserT userT = userDao.findByUsername(username);
		if(userT == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(userT.getUsername(), userT.getPassword(), getAuthority(userT));
	}

	private Set<SimpleGrantedAuthority> getAuthority(UserT userT) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		userT.getRoleTS().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}

	public List<UserT> findAll() {
		List<UserT> list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDao.deleteById(id);
	}

	@Override
	public UserT findOne(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public UserT findById(Long id) {
		return userDao.findById(id).get();
	}

	@Override
    public UserT save(UserDto user) {
	    UserT newUserT = new UserT();
	    newUserT.setUsername(user.getUsername());
	    newUserT.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUserT);
    }
}
