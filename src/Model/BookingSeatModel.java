package Model;

public class BookingSeatModel {
        private int bookingSeatId;
        private int bookingId;
        private int seatId;

    public BookingSeatModel() {}

        

        public BookingSeatModel(int bookingSeatId, int bookingId, int seatId) {
            this.bookingSeatId = bookingSeatId;
            this.bookingId = bookingId;
            this.seatId = seatId;
        }
        public int getBookingSeatId() {
            return bookingSeatId;
        }
        public void setBookingSeatId(int bookingSeatId) {
            this.bookingSeatId = bookingSeatId;
        }
        public int getBookingId() {
            return bookingId;
        }
        public void setBookingId(int bookingId) {
            this.bookingId = bookingId;
        }
        public int getSeatId() {
            return seatId;
        }
        public void setSeatId(int seatId) {
            this.seatId = seatId;
        }
}
