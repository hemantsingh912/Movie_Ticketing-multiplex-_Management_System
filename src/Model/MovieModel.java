package Model;
import java.util.Date;

public class MovieModel {
    private int movieId;
    private String movieName;
    private String genre;
    private String language;
    private int durationMinutes;
    private String description;
    private String posterPath;
    private Date releaseDate;
    private String certificate;
    private String trailerLink;
    private String movieStatus;

    public MovieModel() {}

    

public MovieModel(int movieId, String movieName, String genre, String language, int durationMinutes, String description,
        String posterPath, Date releaseDate, String certificate, String trailerLink, String movieStatus) {
    this.movieId = movieId;
    this.movieName = movieName;
    this.genre = genre;
    this.language = language;
    this.durationMinutes = durationMinutes;
    this.description = description;
    this.posterPath = posterPath;
    this.releaseDate = releaseDate;
    this.certificate = certificate;
    this.trailerLink = trailerLink;
    this.movieStatus = movieStatus;
}

@Override
public String toString() {
    return movieName;
}
public int getMovieId() {
    return movieId;
}
public void setMovieId(int movieId) {
    this.movieId = movieId;
}
public String getMovieName() {
    return movieName;
}
public void setMovieName(String movieName) {
    this.movieName = movieName;
}
public String getGenre() {
    return genre;
}
public void setGenre(String genre) {
    this.genre = genre;
}
public String getLanguage() {
    return language;
}
public void setLanguage(String language) {
    this.language = language;
}
public int getDurationMinutes() {
    return durationMinutes;
}
public void setDurationMinutes(int durationMinutes) {
    this.durationMinutes = durationMinutes;
}
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}
public String getPosterPath() {
    return posterPath;
}
public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
}
public Date getReleaseDate() {
    return releaseDate;
}
public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
}
public String getCertificate() {
    return certificate;
}
public void setCertificate(String certificate) {
    this.certificate = certificate;
}
public String getTrailerLink() {
    return trailerLink;
}
public void setTrailerLink(String trailerLink) {
    this.trailerLink = trailerLink;
}
public String getMovieStatus() {
    return movieStatus;
}
public void setMovieStatus(String movieStatus) {
    this.movieStatus = movieStatus;
}
}
