package com.example;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("simpleUserDetailsService")
public class SimpleUserDetailsService implements UserDetailsService{

	private final KaiinRepository kaiinRepo;
	
	public SimpleUserDetailsService(KaiinRepository kaiinRepo) {
		this.kaiinRepo = kaiinRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return kaiinRepo.findByName(name);
	} 
}
