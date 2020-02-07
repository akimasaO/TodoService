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
	public void register(Kaiin kaiin) {
		String encodedPassword = passwordEncoder.encode(kaiin.getPassword());
        kaiin.setPassword(encodedPassword);
		kaiinRepo.save(kaiin);
	}	
	
	// add API
	public void add(Todo todo) {
		todoRepo.save(todo);
	}
	
	// addDB API
	public void adddb(Todo todo) {
		todoRepo.save(todo);
	}

	// query API
	public List<Todo> query(int userid) {
		return todoRepo.findByUserid(userid);
		
	}

	public Todo delete(int userid, int todoid) {
		Todo todo = todoRepo.findByTodoid(todoid);
		if(todo.getUserId() == userid){
			todoRepo.delete(todo);
			return todo;
		}else {
			return null;
		}
	}

	public void addDbRegi(Kaiin kaiin) {
		// TODO Auto-generated method stub
		String encodedPassword = passwordEncoder.encode(kaiin.getPassword());
        kaiin.setPassword(encodedPassword);
		kaiinRepo.save(kaiin);
	}


}