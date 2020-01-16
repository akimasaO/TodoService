package com.example;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Kaiin {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_user_id_seq")
	@SequenceGenerator(
			name = "user_user_id_seq",
			sequenceName = "userid_seq",
			initialValue = 1,
			allocationSize = 1)
	@Column(name = "userid", nullable = false)
	private int userid;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	public void setUserId(int userid) {this.userid = userid;}
	public int getUserId() {return userid;}
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setPassword(String password) {this.password = password;}
	public String getPassword() {return password;}
}