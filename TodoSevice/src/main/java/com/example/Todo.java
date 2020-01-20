package com.example;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo {
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "todo_todoid_seq") // クラス内でのシーケンス名
	@SequenceGenerator(
			name = "todo_todoid_seq", // クラス内でのシーケンス名
			sequenceName = "todo_seq", // postgresqlのシーケンス名を参照
			initialValue = 1,
			allocationSize = 1)
	@Column(name = "todoid", nullable = false)
	private int todoid;
			
	@Column(name = "userid", nullable = false)
	private int userid;
	
	@Column(name = "content", nullable = false)
	private String content;
	
	@Column(name = "date", nullable = true) // YYYY-MM-DD
	private Date date;
	
	public void setTodoId(int todoid) {this.todoid = todoid;}
	public int getTodoId() {return todoid;}
	public void setUserId(int userid) {this.userid = userid;}
	public int getUserId() {return userid;}
	public void setContent(String content) {this.content = content;}
	public String getContent() {return content;}
	public void setDate(String date) {
		this.date = Date.valueOf(date);
	}
	public Date getDate() {return date;}
}
