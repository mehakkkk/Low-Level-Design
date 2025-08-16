//implement prototype design using cloneable interfce
//cloneable is marker interface i.e an empty interface
// it only does shallow cloning
// in order to do deep cloning the nested object needs to implement cloneable interface
// and override the clone method

public class PrototypeCloneable {

    public static void main(String[] args) throws CloneNotSupportedException {


        //before implementing cloneable
        Student s1 = new Student("Mehak",new Address("New Thippasandra","Bengalurur"));
        Student s2 = s1;
        s1.setName("Gouri");
        System.out.println(s1);
        System.out.println(s2);

        //with the above approach both s1 and s2 are pointing to same memory in the heap
        // hence changin anyone of them will change both this is not cloning

        /**

         Student s3 = new Student("Seema",new Address("Saraf Bazaar","Nashik"));
        Student s4 =(Student) s3.clone();
        s4.setName("Rajeev");
        System.out.println("after cloneable interface on Student");
        System.out.println(s3);
        System.out.println(s4);

        //when cloneable is only implement by Student ad not address, change s4 address
        s4.address.city="agra";
        System.out.println("after cloneable interface on Student and not on address");
        System.out.println(s3);
        System.out.println(s4);

         **/

        //As only shallow copy of address is done change in s4 address object also changes s3 address object
        // as they both point to same address
        Student s3 = new Student("Seema",new Address("Saraf Bazaar","Nashik"));
        Student s4 =(Student) s3.clone();
        s4.setName("Rajeev");
        s4.address.city="agra";
        System.out.println("after cloneable interface on Student and address");
        System.out.println(s3);
        System.out.println(s4);


    }
}

class Address implements Cloneable{
    String street;
    String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Address(street,city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

class Student implements Cloneable{
    String name;
    Address address;

    public Student(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Student(name,(Address) address.clone());
    }
}
