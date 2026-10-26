package Dao;

import Db.DBConnection;
import Model.MovieModel;
import java.sql.*;
import java.util.ArrayList;

public class MovieDAO {

    Connection con;

    public MovieDAO() {
        con = DBConnection.getConnection();
    }

    // Add Movie
    public void addMovie(MovieModel movie) {

        try {

            String sql = "INSERT INTO movie(movieName,genre,language,durationMinutes,description,posterPath,releaseDate,certificate,trailerLink,movieStatus) VALUES(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, movie.getMovieName());
            ps.setString(2, movie.getGenre());
            ps.setString(3, movie.getLanguage());
            ps.setInt(4, movie.getDurationMinutes());
            ps.setString(5, movie.getDescription());
            ps.setString(6, movie.getPosterPath());

            java.sql.Date sqlDate = null;
            if(movie.getReleaseDate() != null) {
                sqlDate = new java.sql.Date(
                        movie.getReleaseDate().getTime());
            }

            ps.setDate(7, sqlDate);

            ps.setString(8, movie.getCertificate());
            ps.setString(9, movie.getTrailerLink());
            ps.setString(10, movie.getMovieStatus());

            int result = ps.executeUpdate();

            if(result > 0)
                System.out.println("Movie Added Successfully");
            else
                System.out.println("Failed");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Show All Movies
  public ArrayList<MovieModel> getMovieList() {

    ArrayList<MovieModel> list = new ArrayList<>();

    try {

        String sql = "SELECT * FROM movie";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()) {

            MovieModel movie = new MovieModel();

            movie.setMovieId(rs.getInt("movieId"));
            movie.setMovieName(rs.getString("movieName"));
            movie.setGenre(rs.getString("genre"));
            movie.setLanguage(rs.getString("language"));
            movie.setDurationMinutes(rs.getInt("durationMinutes"));
            movie.setPosterPath(rs.getString("posterPath"));
            movie.setMovieStatus(rs.getString("movieStatus"));

            list.add(movie);
        }

    } catch(Exception e) {
        e.printStackTrace();
    }

    return list;
}

    // Search Movie
    public MovieModel searchMovie(int movieId) {

        MovieModel movie = null;

        try {

            String sql = "SELECT * FROM movie WHERE movieId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, movieId);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                movie = new MovieModel();

                movie.setMovieId(rs.getInt("movieId"));
                movie.setMovieName(rs.getString("movieName"));
                movie.setGenre(rs.getString("genre"));
                movie.setLanguage(rs.getString("language"));
                movie.setDurationMinutes(rs.getInt("durationMinutes"));
                movie.setDescription(rs.getString("description"));
                movie.setPosterPath(rs.getString("posterPath"));
                movie.setReleaseDate(rs.getDate("releaseDate"));
                movie.setCertificate(rs.getString("certificate"));
                movie.setTrailerLink(rs.getString("trailerLink"));
                movie.setMovieStatus(rs.getString("movieStatus"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return movie;
    }

    // Update Movie
    public void updateMovie(MovieModel movie) {

        try {

            String sql = "UPDATE movie SET movieName=?,genre=?,language=?,durationMinutes=?,description=?,posterPath=?,releaseDate=?,certificate=?,trailerLink=?,movieStatus=? WHERE movieId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, movie.getMovieName());
            ps.setString(2, movie.getGenre());
            ps.setString(3, movie.getLanguage());
            ps.setInt(4, movie.getDurationMinutes());
            ps.setString(5, movie.getDescription());
            ps.setString(6, movie.getPosterPath());

            java.sql.Date sqlDate = null;
            if(movie.getReleaseDate() != null) {
                sqlDate = new java.sql.Date(
                        movie.getReleaseDate().getTime());
            }

            ps.setDate(7, sqlDate);

            ps.setString(8, movie.getCertificate());
            ps.setString(9, movie.getTrailerLink());
            ps.setString(10, movie.getMovieStatus());
            ps.setInt(11, movie.getMovieId());

            int result = ps.executeUpdate();

            if(result > 0)
                System.out.println("Movie Updated");
            else
                System.out.println("Movie Not Found");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Movie
    public void deleteMovie(int movieId) {

        try {

            String sql = "DELETE FROM movie WHERE movieId=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, movieId);

            int result = ps.executeUpdate();

            if(result > 0)
                System.out.println("Movie Deleted");
            else
                System.out.println("Movie Not Found");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    //Return Total Movie For show a Staff home
    public int getMovieCount() {

    int count = 0;

    try {
        String sql = "SELECT COUNT(*) FROM movie";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            count = rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return count;
}

    /*public static void main(String[] args){
            MovieModel movie = new MovieModel();

            movie.setMovieName("Animal");
            movie.setGenre("Action/Drama");
            movie.setLanguage("Hindi");
            movie.setDurationMinutes(201);
            movie.setDescription("Father son action drama");
            movie.setPosterPath("C:\\poster\\animal.jpg");
            movie.setReleaseDate(new java.util.Date());
            movie.setCertificate("A");
            movie.setTrailerLink("https://youtube.com");
            movie.setMovieStatus("Now Showing");

            MovieDAO dao = new MovieDAO();
            dao.addMovie(movie);
    } */
}