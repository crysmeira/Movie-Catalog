package com.crystianemeira.movie_catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crystianemeira.movie_catalog.model.Movie;

public interface Movies extends JpaRepository<Movie, Long> {
	
	public List<Movie> findByTitleContaining(String title);
}
