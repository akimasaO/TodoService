package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

public class SimpleLoginUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	KaiinRepository kaiinRepo;
	
	// kaiin Entity
	private Kaiin kaiin;
	
	public Kaiin getKaiin() {
		return kaiin;
	}
	
	public SimpleLoginUser(Kaiin kaiin) {
		super(kaiin.getUsername(), kaiin.getPassword(), null);
		Kaiin loginkaiin = kaiinRepo.findByName(kaiin.getUsername());
		kaiin.setUserId(loginkaiin.getUserId());
		this.kaiin = kaiin;
	}
}
