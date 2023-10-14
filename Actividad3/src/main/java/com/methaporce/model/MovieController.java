package com.methaporce.model;

import java.util.ArrayList;

public class MovieController {
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    public int getMovieCount(){
        return movies.size();
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Movie> getAvailableMovies() {
        ArrayList<Movie> available_movies = new ArrayList<Movie>();

        for (Movie movie : movies) {
            if (movie.isAvailable()) {
                available_movies.add(movie);
            }
        }

        return available_movies;
    }

    public ArrayList<Movie> getUnavailableMovies() {
        ArrayList<Movie> non_available_movies = new ArrayList<Movie>();

        for (Movie movie : movies) {
            if (!movie.isAvailable()) {
                non_available_movies.add(movie);
            }
        }

        return non_available_movies;
    }

    public Movie getMovieById(int id) {
        for (Movie movie : movies) {
            if (id != movie.getId()) {
                continue;
            }
            return movie;
        }

        // Movie not found
        return null;
    }

    public boolean markMovieAsAvailable(int id) {
        Movie movie = getMovieById(id);

        if (movie == null) {
            // Id doesn't exist
            return false;
        }

        movie.setAvailability(true);
        return true;
    }

    public boolean addMovie(Movie new_movie) {
        if (getMovieById(new_movie.getId()) != null) {
            // Movie already exist
            return false;
        }

        return movies.add(new_movie);
    }

    public boolean deleteMovie(int id) {
        Movie movie = getMovieById(id);

        if (movie == null) {
            // Id doesn't exist
            return false;
        }

        return movies.remove(movie);
    }

}
