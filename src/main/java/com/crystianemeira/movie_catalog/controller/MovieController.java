package com.crystianemeira.movie_catalog.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
	 * @return ModelAndView name of a View responsible for adding a new movie and an instance of Movie
	 */
	@RequestMapping("/new")
	public ModelAndView newMovie() {
		ModelAndView mv = new ModelAndView("RegisterMovie");
		return mv.addObject(new Movie());
	}
	
	/**
	 * Save data about a movie into the database
	 * 
	 * @return ModelAndView name of a View and a message
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Validated Movie movie, Errors errors) {
		ModelAndView mv = new ModelAndView("RegisterMovie");
		if (errors.hasErrors()) {
			return mv;
		}
		
		movies.save(movie);
		
		return mv.addObject("message", "Movie registered!");
	}
	
	/**
	 * Open the screen that show all the movies
	 * 
	 * @return ModelAndView name of a View responsible for showing the movies and a list of all the movies
	 */
	@RequestMapping
	public ModelAndView search() {
		List<Movie> allMovies = movies.findAll();
		return new ModelAndView("SearchMovies").addObject("movies", allMovies);
	}
	
	/**
	 * Return a list with all movies
	 * 
	 * @return List<StatusMovie> list with all movies
	 */
	@ModelAttribute("allStatusMovie")
	public List<StatusMovie> allStatusMovie() {
		return Arrays.asList(StatusMovie.values());
	}
}
