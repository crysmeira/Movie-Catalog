package com.crystianemeira.movie_catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crystianemeira.movie_catalog.model.Movie;

@Controller
@RequestMapping("/movies")
public class MovieController {
	
	/**
	 * Open the screen to add a new movie
	 * 
	 * @return String name of a View responsible for adding a new movie
	 */
	@RequestMapping("/new")
	public String newMovie() {
		return "RegisterMovie";
	}
	
	/**
	 * Save data about a movie into the database
	 * 
	 * @return String name of a View
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String save(Movie movie) {
		// TODO: save
		System.out.println(">>> " + movie.getTitle());
		
		return "RegisterMovie";
	}
}
