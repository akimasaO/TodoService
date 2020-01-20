package com.example;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "member")
public class Kaiin implements UserDetails { //　認証用にUserDetailsを実装する必要があるらしい
	
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void setUserId(int userid) {this.userid = userid;}
	public int getUserId() {return userid;}
	public void setName(String name) {this.name = name;}
	//public String getName() {return name;}
	public void setPassword(String password) {this.password = password;}
	//public String getPassword() {return password;} //被ったからコメントアウト

}