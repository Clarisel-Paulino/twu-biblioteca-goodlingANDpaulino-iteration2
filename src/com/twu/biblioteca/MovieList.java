package com.twu.biblioteca;

import java.util.ArrayList;

public class MovieList {

    // List of all movies in library
    private ArrayList<Movie> movieList;
    // List of movies available for checkout
    public ArrayList<Movie> availMovieList;

    // Current number of movies, used to give movie ID
    public int numMovies;

    /**
     * MovieList Constructor
     * Initializes availMovieList and movieList
     */
    public MovieList(){
        this.availMovieList = new ArrayList<Movie>();
        this.movieList = new ArrayList<Movie>();
        initMovieLists();
    }

    /**
     * Initializes list of all movies and list of available movies
     */
    private void initMovieLists(){
        // Set up movie list
        Movie movie1 = new Movie("Avengers: End Game", "Cool person", 2019, 9);
        Movie movie2 = new Movie("Once upon a Time... In Hollywood", "Quentin Terentino", 2019, 8);
        Movie movie3 = new Movie("Aladdin", "Walt Disney", 1992, 10);

        movie3.setCheckedOut();

        initAddMovie(movie1);
        initAddMovie(movie2);
        initAddMovie(movie3);
    }

    /**
     * initAddMovie
     * @param movie - instance of Movie object
     * adds movie to movieList (and availMovieList) and sets the movie ID
     */
    private void initAddMovie(Movie movie){
        numMovies++;
        movie.setId(numMovies);
        this.movieList.add(movie);

        // Only add movie to availMovieList if not checked out
        if(!movie.isCheckedOut()){
            this.availMovieList.add(movie);
        }
    }

    /**
     * getMovieByID
     * @param idString - movie id as a string
     * @return Movie object with the idString as its id
     */
    public Movie getMovieByID(String idString){
        int id = Integer.parseInt(idString);
        return movieList.get(id - 1);
    }

    /**
     * prints availMovieList
     * @return list of available movies as a String, with formatting for columns
     */
    public String toString(){
        String i = String.format("%-10s", "MOVIE ID");
        String t = String.format("%-20s", "TITLE");
        String d = String.format("%-20s", "DIRECTOR");
        String y = String.format("%-20s", "YEAR");
        String r = String.format("%-20s", "RATING");
        String line = "\n================================================================\n";

        StringBuilder sb = new StringBuilder();
        sb.append(i+ t + d + y + r + line);

        for(Movie movie : availMovieList){
            sb.append(movie.toString());
        }
        return sb.toString();
    }

    public ArrayList<Movie> getMovieList(){
        return availMovieList;
    }

    /**
     * updateAvailMovies
     * @param movie -- Movie object to be removed
     *  Removes movie from the list of available movies
     */
    public void updateAvailMovies(Movie movie){
        if (movie.isCheckedOut()){
            availMovieList.remove(movie);
        }
        else{
            availMovieList.add(movie);
        }
    }
}
