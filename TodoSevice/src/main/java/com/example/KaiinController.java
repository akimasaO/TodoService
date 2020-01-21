package com.example;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	
	// login/logout API
	// Security Configurationの設定で有効になるため実装は不要です。
	
	// register API〇
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public ResponseEntity<Kaiin> register(
			@RequestParam("name") String name,
			@RequestParam("password") String password) {
		Kaiin kaiin = new Kaiin();
		kaiin.setName(name);
		kaiinService.register(kaiin,password);
		return ResponseEntity.ok(kaiin);
	}
	
	
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseEntity<Todo> add(
			@AuthenticationPrincipal SimpleLoginUser loginUser,
			@RequestParam("date") String date,
			@RequestParam("content") String content) {
		Kaiin loginKaiin = loginUser.getKaiin();
		Todo todo = new Todo();
		todo.setDate(date);
		todo.setContent(content);
		todo.setUserId(loginKaiin.getUserId());
		kaiinService.add(todo);
		
		return ResponseEntity.ok(todo);
	}
	
	@RequestMapping(path = "/query", method = RequestMethod.POST)
	public ResponseEntity<List<Todo>> query(
			@AuthenticationPrincipal SimpleLoginUser loginUser) {
		Kaiin loginKaiin = loginUser.getKaiin();
		System.out.println(loginKaiin.getName());
		System.out.println(loginKaiin.getUserId());
		return ResponseEntity.ok(kaiinService.query(loginKaiin));
	}
	
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Todo> delete(
			@RequestParam Integer todoid,
			@AuthenticationPrincipal SimpleLoginUser loginUser) {
		Todo todo = new Todo();
		todo.setTodoId(todoid);
				
		return ResponseEntity.ok(kaiinService.delete(todo));
		
	}
	
	

}