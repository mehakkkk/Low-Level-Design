import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DriverRepository {
    private Map<UUID,Driver> driverMap;
    private static DriverRepository instance;

    private DriverRepository()
    {
        driverMap = new ConcurrentHashMap<>();
    }

    public static DriverRepository getInstance()
    {
        if(instance == null)
        {
            synchronized (UserRepository.class)
            {
                if(instance == null)
                {
                    instance = new DriverRepository();
                }
            }
        }
        return instance;
    }

    public boolean addDriver(Driver driver)
    {
        driverMap.put(driver.getId(),driver);
        return true;
    }

    public boolean deleteUser(String driverId)
    {
        driverMap.remove(UUID.fromString(driverId));
        return true;
    }

    public boolean userExists(String driverId)
    {
        return driverMap.containsKey(UUID.fromString(driverId));
    }

    public Driver getUser(String driverId)
    {
        return driverMap.get(UUID.fromString(driverId));
    }

    public List<Driver> getDrivers()
    {
        return driverMap.values().stream().toList();
    }

    public boolean updateUser(Driver driver)
    {
        return addDriver(driver);
    }
}
