public class Seat {
    private String seatId;
    private SeatType seatType;
    private char row;
    private boolean isBooked;

    public Seat(String seatId, SeatType seatType, char row) {
        this.seatId = seatId;
        this.seatType = seatType;
        this.row = row;
    }

    public String getSeatId() {
        return seatId;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public char getRow() {
        return row;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        this.isBooked = true;
    }

    public void cancel() {
        this.isBooked = false;
    }
}
