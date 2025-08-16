import java.time.LocalDateTime;

public class WhatsAppNotification implements NotificationSystem{
    @Override
    public void sendMessage(String message) {
        System.out.println("Whatsapp message:: "+message);
    }
}
