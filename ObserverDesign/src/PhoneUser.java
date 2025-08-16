public class PhoneUser implements IObserver{
    String name;

    public PhoneUser(String name)
    {
        this.name = name;
    }
    @Override
    public void update(Object message) {
        System.out.println("Hello "+name +" "+message);
    }

}
