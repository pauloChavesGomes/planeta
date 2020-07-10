package com.planeta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planeta.modelo.Planeta;

public interface PlanetaRepository extends JpaRepository<Planeta, Long>{
	Planeta findById(long id);
	Planeta findByNome(String nome);
	
	
	
}

