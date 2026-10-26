package Model;

public class SeatPriceModel {
        private int seatPriceId;
        private int showId;
        private String seatType;
        private double price;

    public SeatPriceModel() {}

        

public SeatPriceModel(int seatPriceId, int showId, String seatType, double price) {
    this.seatPriceId = seatPriceId;
    this.showId = showId;
    this.seatType = seatType;
    this.price = price;
}
public int getSeatPriceId() {
    return seatPriceId;
}
public void setSeatPriceId(int seatPriceId) {
    this.seatPriceId = seatPriceId;
}
public int getShowId() {
    return showId;
}
public void setShowId(int showId) {
    this.showId = showId;
}
public String getSeatType() {
    return seatType;
}
public void setSeatType(String seatType) {
    this.seatType = seatType;
}
public double getPrice() {
    return price;
}
public void setPrice(double price) {
    this.price = price;
}
}
