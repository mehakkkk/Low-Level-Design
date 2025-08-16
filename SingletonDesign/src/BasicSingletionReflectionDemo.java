import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Even though the below depicts singleton design pattern, but reflection can ccess private constructor
 * which violates the singleton principle
 * ********Solution***********
 * To solve the issue, when constructor returns the instance, make sure to check whether for the class
 * an instance already holds, if yes return runtime exception
 **/

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BasicSingletionReflectionDemo {
    public static void main(String[] args) {
        try {
            System.out.println("Solving the reflection issue");

            // Get the class object for BasicSingletonReflection
            Class<BasicSingletonReflection> singletonClass = (Class<BasicSingletonReflection>) Class.forName("BasicSingletonReflection").asSubclass(BasicSingletonReflection.class);
            System.out.println("Class obtained: " + singletonClass.getName());

            // Access the private constructor
            Constructor<BasicSingletonReflection>[] singletonConstructors = (Constructor<BasicSingletonReflection>[]) singletonClass.getDeclaredConstructors();
            for (Constructor<BasicSingletonReflection> constructor : singletonConstructors) {
                constructor.setAccessible(true);

                try {
                    // Create instances using reflection
                    System.out.println("Attempting to create the first instance");
                    BasicSingletonReflection cons1 = constructor.newInstance();
                    System.out.println("First instance created: " + cons1);

                    System.out.println("Attempting to create the second instance");
                    BasicSingletonReflection cons2 = constructor.newInstance();
                    System.out.println("Second instance created: " + cons2);

                    // Check if the two instances are the same
                    System.out.println("cons1 == cons2: " + (cons1 == cons2));
                } catch (Exception e) {
                    System.out.println("Exception during instance creation: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class BasicSingletonReflection {

    private static BasicSingletonReflection INSTANCE;

    static {
        try {
            INSTANCE = new BasicSingletonReflection();
            System.out.println("Singleton instance created in static block: " + INSTANCE);
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while creating singleton instance.", e);
        }
    }

    private BasicSingletonReflection() {
        // This should prevent the creation of another instance if one already exists
        if (INSTANCE != null) {
            throw new RuntimeException("Only a single instance of the class is present");
        }
    }

    public static BasicSingletonReflection getInstance() {
        return INSTANCE;
    }
}
