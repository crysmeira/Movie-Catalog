package com.crystianemeira.movie_catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crystianemeira.movie_catalog.model.Movie;
import com.crystianemeira.movie_catalog.model.StatusMovie;
import com.crystianemeira.movie_catalog.repository.Movies;
import com.crystianemeira.movie_catalog.repository.filter.CatalogFilter;

@Service
public class RegisterMovieService {
	@Autowired
	private Movies movies;
	
	/**
	 * Save data about a movie into the database
	 * 
	 * @param movie an instance of Movie
	 */
	public void save(Movie movie) {
		movies.save(movie);
	}
	
	/**
	 * Delete a movie
	 * 
	 * @param id the id of the movie to delete
	 */
	public void delete(Long id) {
		movies.delete(id);
	}
	
	/**
	 * Change the status of a movie from NOT WATCHED to WATCHED
	 * 
	 * @param id the id of the movie to be updates
	 * 
	 * @return String description about the status of the movie
	 */
	public String check(Long id) {
		Movie movie = movies.findOne(id);
		movie.setStatus(StatusMovie.WATCHED);
		movies.save(movie);
		
		return StatusMovie.WATCHED.getDescription();
	}
	
	public List<Movie> filter(CatalogFilter filter) {
		String title = (filter.getTitle() == null) ? "%" : filter.getTitle();
		return movies.findByTitleContaining(title);
	}
}
