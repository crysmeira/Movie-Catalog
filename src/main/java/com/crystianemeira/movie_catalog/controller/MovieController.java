package com.crystianemeira.movie_catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {
	
	@RequestMapping("/movies/new")
	public String newMovie() {
		return "RegisterMovie";
	}
}
