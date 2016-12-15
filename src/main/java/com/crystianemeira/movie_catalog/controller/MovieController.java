package com.crystianemeira.movie_catalog.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crystianemeira.movie_catalog.model.Movie;
import com.crystianemeira.movie_catalog.model.StatusMovie;
import com.crystianemeira.movie_catalog.repository.Movies;

@Controller
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private Movies movies;
	
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
	 * @return ModelAndView name of a View and a message
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(Movie movie) {
		movies.save(movie);
		
		return new ModelAndView("RegisterMovie").addObject("message", "Movie registered!");
	}
	
	/**
	 * Open the screen that show all the movies
	 * 
	 * @return String name of a View responsible for showing the movies
	 */
	@RequestMapping
	public String search() {
		return "SearchMovies";
	}
	
	@ModelAttribute("allStatusMovie")
	public List<StatusMovie> allStatusMovie() {
		return Arrays.asList(StatusMovie.values());
	}
}
