import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GenericObjectPoolManager<T> {
    private final int MAX_SIZE = 5;
    private final int POOL_SIZE = 3;
    private List<T> freeList;
    private List<T> usedList;
    private static GenericObjectPoolManager<?> instance;
    private Class<T> type;

    // Updated getInstance method, now accepts Class<T> as a parameter
    public static <T> GenericObjectPoolManager<T> getInstance(Class<T> type) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (instance == null) {
            instance = new GenericObjectPoolManager<T>(type);
        }
        return (GenericObjectPoolManager<T>) instance;
    }

    // Constructor now accepts Class<T> as a parameter
    private GenericObjectPoolManager(Class<T> type) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.type = type; // Capture the type of T
        this.freeList = new ArrayList<>();
        this.usedList = new ArrayList<>();

        // Create the initial pool of objects
        for (int i = 0; i < POOL_SIZE; i++) {
            freeList.add(type.getDeclaredConstructor().newInstance());
        }
    }

    public T borrowObject() {
        if (freeList.isEmpty()) {
            return null; // Pool is empty
        }
        T object = freeList.remove(freeList.size() - 1);
        usedList.add(object);
        return object;
    }

    // Return an object to the pool
    public void returnObject(T object) {
        if (usedList.remove(object)) {
            freeList.add(object);
        }
    }

    // Check the current size of the pool
    public int getFreeSize() {
        return freeList.size();
    }

    public int getUsedSize() {
        return usedList.size();
    }

    public static void main(String[] args) {
        try {
            // Pass the class type (DbConnection.class) when getting the instance
            GenericObjectPoolManager<DbConnection> dbConnectionGenericObjectPoolManager = GenericObjectPoolManager.getInstance(DbConnection.class);
            System.out.println("Free pool size: " + dbConnectionGenericObjectPoolManager.getFreeSize());
            System.out.println("Used pool size: " + dbConnectionGenericObjectPoolManager.getUsedSize());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


