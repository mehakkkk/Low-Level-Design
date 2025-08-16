public class File implements Component{
    String name;

    public File(String name)
    {
        this.name = name;
    }

    @Override
    public void ls() {
        System.out.println(name);
    }
}
