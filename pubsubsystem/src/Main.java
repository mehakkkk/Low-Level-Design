public class Main {
    public static void main(String[] args) {
        Topic topic1 = new Topic("call");
        Topic topic2 = new Topic("sms");

        Subscriber s1 = new Subscriber("comcast");
        Subscriber s2 = new Subscriber("verizon");

        topic1.addSubscriber(s1);
        topic2.addSubscriber(s1);

        topic2.addSubscriber(s2);

        Publisher publisher = new Publisher();
        publisher.registerTopic(topic1);
        publisher.registerTopic(topic2);
        publisher.publish(topic1,new Message("Hi from topic 1"));
        publisher.publish(topic2,new Message("Hi from topic 2"));

        topic1.shutdown();
        topic2.shutdown();
    }
}
