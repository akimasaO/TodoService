package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.example.Kaiin;

@Service
public class KaiinService implements UserDetailsService{
	
	@Autowired
	KaiinRepository kaiinRepo;
	
	@Autowired
	TodoRepository todoRepo;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		
		if(StringUtils.isEmpty(name)) {
			throw new UsernameNotFoundException("");
		}
		
		Kaiin kaiin = kaiinRepo.findByName(name);
		if(kaiin == null) {
			throw new UsernameNotFoundException("");
		}
		return kaiin;
	}

	// register API
	public void register(Kaiin kaiin, String rawPassword) {
		String encodedPassword = passwordEncoder.encode(rawPassword);
        kaiin.setPassword(encodedPassword);
		kaiinRepo.save(kaiin);
	}	
	
	// add API
	public void add(Todo todo, Kaiin loginkaiin) {
		Kaiin kaiin = kaiinRepo.findByName(loginkaiin.getUsername());
		todo.setUserId(kaiin.getUserId());
		todoRepo.save(todo);
	}

	// query API
	public List<Todo> query(Kaiin loginkaiin) {
		Kaiin kaiin = kaiinRepo.findByName(loginkaiin.getUsername());
		return todoRepo.findByUserid(kaiin.getUserId());
		
	}

	public Todo delete(Todo todo, Kaiin loginkaiin) {
		todo = todoRepo.findByTodoid(todo.getTodoId());
		Kaiin kaiin = kaiinRepo.findByName(loginkaiin.getUsername());
		if(todo.getUserId() == kaiin.getUserId()){
			todoRepo.delete(todo);
			return todo;
		}else {
			return null;
		}
	}



}