import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    String licenseNumber;
    String id;
    List<Reservation> history;

    public User(String name, String licenseNumber, String id) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.id = id;
        history = new ArrayList<>();
    }
}
