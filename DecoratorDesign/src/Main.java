public class Main {
    public static void main(String[] args) {
        Notification emailNotification = new EmailNotifier();
        emailNotification.sendNotification("Hello, this is my email notification");

        //now log the message before seding
        Notification logEmailNotification = new LoogingDecorator(emailNotification);
        logEmailNotification.sendNotification("Hello, this is email notification and will be logged");

        Notification textNotifier = new TextNotifier();
        textNotifier.sendNotification("Hello, this is text message");

        textNotifier = new EncryptionDecorator(textNotifier);
        textNotifier.sendNotification("hello, this is text message and will be encrypted");

    }
}
