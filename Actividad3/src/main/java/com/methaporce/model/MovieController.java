package com.methaporce.model;

import java.util.ArrayList;

public class MovieController {
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    public ArrayList<Movie> GetMovies() {
        return movies;
    }

    public ArrayList<Movie> GetAvailableMovies() {
        ArrayList<Movie> available_movies = new ArrayList<Movie>();

        for (Movie movie : movies) {
            if (movie.isAvailable()) {
                available_movies.add(movie);
            }
        }

        return available_movies;
    }

    public ArrayList<Movie> GetUnavailableMovies() {
        ArrayList<Movie> non_available_movies = new ArrayList<Movie>();

        for (Movie movie : movies) {
            if (!movie.isAvailable()) {
                non_available_movies.add(movie);
            }
        }

        return non_available_movies;
    }

    public Movie GetMovieById(int id) {
        for (Movie movie : movies) {
            if (id != movie.getId()) {
                continue;
            }
            return movie;
        }

        // Movie not found
        return null;
    }

    public boolean MarkMovieAsAvailable(int id) {
        Movie movie = GetMovieById(id);

        if (movie == null) {
            // Id doesn't exist
            return false;
        }

        movie.setAvailability(true);
        return true;
    }

    public boolean AddMovie(Movie new_movie) {
        if (GetMovieById(new_movie.getId()) != null) {
            // Movie already exist
            return false;
        }

        return movies.add(new_movie);
    }

    public boolean deleteMovie(int id) {
        Movie movie = GetMovieById(id);

        if (movie == null) {
            // Id doesn't exist
            return false;
        }

        return movies.remove(movie);
    }

}
