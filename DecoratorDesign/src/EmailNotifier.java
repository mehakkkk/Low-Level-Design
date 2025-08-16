public class EmailNotifier implements Notification{
    @Override
    public void sendNotification(String message) {
        //logic to send email notification
        System.out.println("notification sent via email: "+message);
    }
}
