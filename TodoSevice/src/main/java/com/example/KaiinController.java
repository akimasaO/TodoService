package com.example;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@ResponseBody
public class KaiinController {
	@Autowired
	KaiinService kaiinService;
	
	//疎通用・後で消す
	@RequestMapping(path = "/")
	public String plean() {
		return "Hello World";
	}
	
	// register API
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public ResponseEntity<Kaiin> register(
			@RequestParam("name") String name,
			@RequestParam("password") String password) {
		Kaiin kaiin = new Kaiin();
		kaiin.setName(name);
		kaiinService.register(kaiin,password);
		return ResponseEntity.ok(kaiin);
		
	}
	
	@RequestMapping(path = "/query", method = RequestMethod.GET)
	public ResponseEntity<List<Kaiin>> query() {
		
		return ResponseEntity.ok(kaiinService.query());
	}
	
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Kaiin> delete(@RequestParam int userid) {
		Kaiin kaiin = new Kaiin();
		kaiin.setUserId(userid);
		
		kaiinService.delete(kaiin);
		return ResponseEntity.ok(kaiin);
		
	}
}