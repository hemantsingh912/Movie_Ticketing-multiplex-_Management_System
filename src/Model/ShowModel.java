package Model;

import java.sql.Date;
import java.sql.Time;

public class ShowModel {

    private int showId;
    private int movieId;
    private int screenId;
    private Date showDate;
    private Time showTime;
    private String showStatus;

    public ShowModel() {}

    public ShowModel(int showId, int movieId, int screenId, Date showDate, Time showTime, String showStatus) {
        this.showId = showId;
        this.movieId = movieId;
        this.screenId = screenId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.showStatus = showStatus;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public Time getShowTime() {
        return showTime;
    }

    public void setShowTime(Time showTime) {
        this.showTime = showTime;
    }

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }
}