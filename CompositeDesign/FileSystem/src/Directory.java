import java.util.ArrayList;
import java.util.List;

public class Directory implements Component{

    String name;
    List<Component> componentList;

    public Directory(String name)
    {
        this.name = name;
        componentList = new ArrayList<>();
    }

    public void add(Component component) {
        this.componentList.add(component);
    }

    @Override
    public void ls() {
        System.out.println("Directory name " + this.name);

        for(Component component:componentList)
        {
            System.out.print("*");
            component.ls();
        }
    }
}
