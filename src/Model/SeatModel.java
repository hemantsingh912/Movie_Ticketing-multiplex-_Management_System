package Model;

public class SeatModel {

    private int seatId;
    private int screenId;
    private String seatNumber;
    private String seatType;
    private String seatStatus;

    public SeatModel() {}

    

public SeatModel(int seatId, int screenId, String seatNumber, String seatType, String seatStatus) {
    this.seatId = seatId;
    this.screenId = screenId;
    this.seatNumber = seatNumber;
    this.seatType = seatType;
    this.seatStatus = seatStatus;
}
public int getSeatId() {
    return seatId;
}
public void setSeatId(int seatId) {
    this.seatId = seatId;
}
public int getScreenId() {
    return screenId;
}
public void setScreenId(int screenId) {
    this.screenId = screenId;
}
public String getSeatNumber() {
    return seatNumber;
}
public void setSeatNumber(String seatNumber) {
    this.seatNumber = seatNumber;
}
public String getSeatType() {
    return seatType;
}
public void setSeatType(String seatType) {
    this.seatType = seatType;
}
public String getSeatStatus() {
    return seatStatus;
}
public void setSeatStatus(String seatStatus) {
    this.seatStatus = seatStatus;
}
}
