package com.example.Ruokavalio.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RavintoRepository extends CrudRepository<Ravinto, Long> {
	List<Ravinto> findByRavintoNimi(String ravintoNimi);
}
