import java.util.ArrayList;
import java.util.List;

public class Screen {
    List<Seat> seats;
    int seatsPerRow;
    int totCapactiy;
    int totRow;

    public Screen(int seatsPerRow, int totRow) {
        if (totRow < 5) {
            throw new IllegalArgumentException("A screen must have at least 5 rows.");
        }

        this.seats = new ArrayList<>();
        this.seatsPerRow = seatsPerRow;
        this.totCapactiy = seatsPerRow*totRow;
        this.totRow = totRow;

        int reclineRows = 2;
        int premiumRows = totRow / 3;
        int regularRows = totRow - premiumRows - reclineRows;

        char rowLabel = 'A';
        for (int row = 0; row < totRow; row++) {
            SeatType category;

            if (row < regularRows) {
                category = SeatType.ECONOMY;
            } else if (row < regularRows + premiumRows) {
                category = SeatType.PREMIUM;
            } else {
                category = SeatType.RECLINER;
            }

            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                seats.add(new Seat( String.valueOf(rowLabel+seatNum), category,rowLabel));
            }
            rowLabel++;
        }
    }

    public List<Seat> getSeats(){
        return seats;
    }
}
