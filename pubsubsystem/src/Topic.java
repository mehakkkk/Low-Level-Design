import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Topic {
    String id;
    String name;
    private final ExecutorService executor = Executors.newCachedThreadPool(); // Thread pool for concurrent delivery
    private final Set<Subscriber> subscribers = new CopyOnWriteArraySet<>();
    public Topic(String name)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void shutdown()
    {
        executor.shutdown();
    }

    public void publish(Message message) {
        for (Subscriber subscriber : subscribers) {
            executor.submit(() -> subscriber.onMessage(message)); // Process messages concurrently
        }
    }
}
