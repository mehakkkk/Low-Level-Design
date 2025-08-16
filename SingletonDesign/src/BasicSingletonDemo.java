import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BasicSingletonDemo {
    public static void main(String[] args) {
        BasicSingleton basicSingleton1 = BasicSingleton.getInstance();
        BasicSingleton basicSingleton2 = BasicSingleton.getInstance();

        System.out.println(basicSingleton1 == basicSingleton2);



    }
}

class BasicSingleton {

    private static BasicSingleton INSTANCE=null;

    private BasicSingleton()
    {
        INSTANCE = this;
    }

    public static BasicSingleton getInstance()
    {
        if(INSTANCE == null){
            INSTANCE = new BasicSingleton();
        }
        return INSTANCE;
    }
}
