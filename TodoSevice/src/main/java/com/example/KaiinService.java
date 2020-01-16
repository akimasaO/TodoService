package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Kaiin;

@Service
public class KaiinService {
	
	@Autowired
	KaiinRepository kaiinRepo;
	@Autowired
    PasswordEncoder passwordEncoder;

	// register API
	public void register(Kaiin kaiin, String rawPassword) {
		String encodedPassword = passwordEncoder.encode(rawPassword);
        kaiin.setPassword(encodedPassword);
		kaiinRepo.save(kaiin);
	}	
	
	public List<Kaiin> query() {
		return kaiinRepo.findAll();
		
	}

	public void delete(Kaiin kaiin) {
		List<Kaiin> kaiinList = kaiinRepo.findByUserid(kaiin.getUserId());
		kaiin.setName(kaiinList.get(0).getName());
		kaiinRepo.delete(kaiin);
	}
}
