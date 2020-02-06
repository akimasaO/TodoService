package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KaiinRepository extends JpaRepository<Kaiin,Long> {
	
	public Kaiin findByUserid(int userid); 
	public Kaiin findByName(String name); 
}