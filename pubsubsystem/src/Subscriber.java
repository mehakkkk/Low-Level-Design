import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Subscriber {
    String id;
    String name;
    private final BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();

    public Subscriber(String name) {
        id = UUID.randomUUID().toString();
        this.name = name;
        startProcessing(); // Start background message processing
    }

    private void startProcessing() {
        Thread worker = new Thread(() -> {
            try {
                while (true) {
                    Message message = messageQueue.take(); // Blocks if queue is empty
                    processMessage(message);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        worker.setDaemon(true);
        worker.start();
    }

    public void onMessage(Message message) {
        messageQueue.offer(message); // Queue messages instead of printing immediately
    }

    private void processMessage(Message message) {
        try {
        Thread.sleep(5000); // Simulate slow processing (5 seconds)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Subscriber " + name + " received: " + message.getContent());

    }
}
