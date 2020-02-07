package com.example;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	// ユーザ登録API
	@RequestMapping(path = "/user", method = RequestMethod.POST)
	public ResponseEntity<Kaiin> register(
			@RequestBody Kaiin kaiin) { // @RequestBodyを付与することでEntityインスタンスにJSONデータがマッピングされます。
		kaiinService.register(kaiin);
		return ResponseEntity.ok(kaiin);
	}
	
	// Todo追加API
	@RequestMapping(value = "/todo/{userid}", method = RequestMethod.POST)
	public ResponseEntity<Todo> add(
			@RequestBody Todo todo,
			@PathVariable int userid) {
		todo.setUserId(userid);
		kaiinService.add(todo);
		
		return ResponseEntity.ok(todo);
	}
	
//	// 実験用Todo追加API
//	@RequestMapping(value = "/todo/adddbdata/{userid}", method = RequestMethod.POST)
//	public ResponseEntity<Todo> adddate(
//			@RequestBody Todo todo,
//			@PathVariable int userid) {
//		todo.setUserId(userid);
//		kaiinService.adddb(todo);
//		
//		return ResponseEntity.ok(todo);
//	}
//	
	// Todo検索API 
	@RequestMapping(value = "/todo/{userid}", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> query(
			@PathVariable int userid) {
			List<Todo> todoList =  kaiinService.query(userid);
		return ResponseEntity.ok(todoList);
	}
	
	// Todo削除API
	@RequestMapping(value = "/todo/{userid}/{todoid}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponceMessage> delete(
			@PathVariable int userid,
			@PathVariable int todoid) {
		kaiinService.delete(userid,todoid);
		ResponceMessage resMessa = new ResponceMessage();
		resMessa.setMessage("Todo削除完了");
		
		return ResponseEntity.ok(resMessa);
		
	}
	
	// DB用ユーザパス追加API
	@RequestMapping(path = "/user/adddbdata/{userid}", method = RequestMethod.PUT)
	public ResponseEntity<Kaiin> addPass(
			@PathVariable int userid,
			@RequestBody Kaiin kaiin) { 
		kaiin.setUserId(userid);
		kaiinService.addDbRegi(kaiin);
		return ResponseEntity.ok(kaiin);
	}
	
}