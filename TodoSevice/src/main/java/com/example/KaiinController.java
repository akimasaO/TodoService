package com.example;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
			@RequestParam("date") String date,
			@RequestParam("content") String content) {
		Kaiin loginkaiin = (Kaiin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Todo todo = new Todo();
		todo.setDate(date);
		todo.setContent(content);
		kaiinService.add(todo,loginkaiin);
		
		return ResponseEntity.ok(todo);
	}
	
	@RequestMapping(path = "/query", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> query() {
		Kaiin loginkaiin = (Kaiin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(kaiinService.query(loginkaiin));
	}
	
	
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Todo> delete(@RequestParam int todoid) {
		Kaiin loginkaiin = (Kaiin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Todo todo = new Todo();
		todo.setTodoId(todoid);
				
		return ResponseEntity.ok(kaiinService.delete(todo, loginkaiin));
		
	}
}