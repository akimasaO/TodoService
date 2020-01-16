package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KaiinRepository extends JpaRepository<Kaiin,Long> {
	
	public List<Kaiin> findByUserid(int userid); 
}