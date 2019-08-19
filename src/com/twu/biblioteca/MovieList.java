package com.twu.biblioteca;

public class MovieList extends RentalList {
    /**
     * MovieList Constructor
     * Initializes availMovieList and movieList
     */
    public MovieList(){
        initMovieLists();
    }

    /**
     * Initializes list of all movies and list of available movies
     */
    private void initMovieLists(){
        // Set up movie list
        Movie movie1 = new Movie("Avengers: End Game", "Cool person", 2019, 9);
        Movie movie2 = new Movie("Once upon a Time...", "Quentin T", 2019, 8);
        Movie movie3 = new Movie("Aladdin", "Walt Disney", 1992, 10);

        movie3.setCheckedOut();

        initAdd(movie1);
        initAdd(movie2);
        initAdd(movie3);
    }

    /**
     * prints availMovieList
     * @return list of available movies as a String, with formatting for columns
     */
    public String toString(){
        String i = String.format("%-10s", "MOVIE ID");
        String t = String.format("%-30s", "TITLE");
        String d = String.format("%-20s", "DIRECTOR");
        String y = String.format("%-10s", "YEAR");
        String r = String.format("%-20s", "RATING");
        String line = "\n================================================================================\n";

        StringBuilder sb = new StringBuilder();
        sb.append(i + t + d + y + r + line);

        for(RentalItem rental : getAvailItemList()){
            sb.append(rental.toString());
        }
        return sb.toString();
    }
}
