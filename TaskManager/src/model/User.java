package model;

import java.util.Objects;

public class User {

    String id;
    String name;
    String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if both objects are the same instance
        if (obj == null || getClass() != obj.getClass()) return false; // Null or different class
        User user = (User) obj;
        return email != null && email.equals(user.email); // Check if emails are equal
    }

    @Override
    public int hashCode() {
        // Use Objects.hash to generate a hash code based on email
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
