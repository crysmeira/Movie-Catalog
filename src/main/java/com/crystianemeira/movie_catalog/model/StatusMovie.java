package com.crystianemeira.movie_catalog.model;

public enum StatusMovie {
	WATCHED("Watched"),
	NOT_WATCHED("Not Watched");
	
	private String description;
	
	StatusMovie(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
