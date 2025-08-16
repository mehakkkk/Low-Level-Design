import java.util.Date;
import java.util.UUID;

public class User {
    private UUID id;
    private Date dob;
    private String email;
    private String password;

    public User(Date dob, String email, String password) {
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.id = UUID.randomUUID();
    }

    public String getId() {
        return id.toString();
    }


    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
