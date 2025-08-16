public abstract class Decorator implements Notification {
    Notification notification;

    public Decorator(Notification notification) {
        this.notification = notification;
    }

    @Override
    public void sendNotification(String message) {
        notification.sendNotification(message);
    }
}
