import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;
//above dependency is required forthis approach

public class SerializableCopy {

    public static void main(String[] args) {
        Person p1 = new Person("Gouri",new PersonAddress("Nashik",422001));
        Person p2 = SerializableUtils.copy();
    }
}


class Person implements Serializable {
    String name;
    PersonAddress address;

    public Person(String name, PersonAddress address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

class PersonAddress implements Serializable{
    String city;
    int pincode;

    public PersonAddress(String city, int pincode) {
        this.city = city;
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "PersonAddress{" +
                "city='" + city + '\'' +
                ", pincode=" + pincode +
                '}';
    }
}

