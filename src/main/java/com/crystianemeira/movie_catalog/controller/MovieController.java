package com.crystianemeira.movie_catalog.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crystianemeira.movie_catalog.model.Movie;
import com.crystianemeira.movie_catalog.model.StatusMovie;
import com.crystianemeira.movie_catalog.repository.filter.CatalogFilter;
import com.crystianemeira.movie_catalog.service.RegisterMovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private RegisterMovieService registerMovieService;
	
	/**
	 * Open the screen to add a new movie
	 * 
	 * @return ModelAndView the View responsible for registering a new movie and a new instance of Movie
	 */
	@RequestMapping("/new")
	public ModelAndView newMovie() {
		ModelAndView mv = new ModelAndView("RegisterMovie");
		return mv.addObject(new Movie());
	}
	
	/**
	 * Save data about a movie into the database
	 * 
	 * @param Movie an instance of Movie
	 * @param Errors information about the object
	 * @param RedirectAttributes used to add attributes to the model
	 * 
	 * @return String the name of the View responsible for registering a new movie
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String save(@Validated Movie movie, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return "RegisterMovie";
		}
		
		registerMovieService.save(movie);
		attributes.addFlashAttribute("message", "Movie registered!");
		
		return "redirect:/movies/new";
	}
	
	/**
	 * Find movies based on a filter (if filter is null, an instance is created to make the search)
	 * 
	 * @param CatalogFilter contains information about the title or genre to be searched
	 * 
	 * @return ModelAndView name of a View responsible for showing the movies and a list of all the movies that respect the filter to fill the View
	 */
	@RequestMapping
	public ModelAndView search(@ModelAttribute("filter") CatalogFilter filter) {
		List<Movie> allMovies = registerMovieService.filter(filter);
		return new ModelAndView("SearchMovies").addObject("movies", allMovies);
	}
	
	/**
	 * Edit a movie
	 * 
	 * @param Movie an existing Movie object
	 * 
	 * @return ModelAndView the View responsible for register a movie
	 */
	@RequestMapping("{id}")
	public ModelAndView edit(@PathVariable("id") Movie movie) {
		ModelAndView mv = new ModelAndView("RegisterMovie");
		return mv.addObject(movie);
	}
	
	/**
	 * Delete a movie
	 * 
	 * @param Long the id of the movie to delete
	 * @param RedirectAttributes used to add attributes to the model
	 * 
	 * @return String the name of the View responsible for showing the movies
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		registerMovieService.delete(id);
		attributes.addFlashAttribute("message", "Movie deleted!");
		
		return "redirect:/movies";
	}
	
	/**
	 * Delete a movie
	 * 
	 * @param Long the id of the movie to change the status
	 * 
	 * @return String description about the status of the movie
	 */
	@RequestMapping(value = "/{id}/check", method = RequestMethod.PUT)
	public @ResponseBody String check(@PathVariable Long id) {
		return registerMovieService.check(id);
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
