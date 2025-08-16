public class Main {
    public static void main(String[] args) {
        IObservable iphone = new IphoneObservable();
        IObservable samsung = new SamsungObservable();

        IObserver user1 = new PhoneUser("Mehak");
        IObserver user2 = new PhoneUser("Gouri");
        IObserver user3 = new PhoneUser("Riya");
        IObserver user4 = new PhoneUser("Gia");

        //track iphone in stock
        iphone.register(user1);
        iphone.register(user2);
        iphone.register(user3);


        //track samsung in stock
        samsung.register(user4);

        //update iphone stock
        iphone.update(5);

        //update samsung stock
        samsung.update(2);


        //now the observable is generic, lets make it work for subscriber subscribing youtube channel
        IObservable cookingChannel = new CookingChannelObservable();
        cookingChannel.register(user2);
        cookingChannel.register(user3);

        cookingChannel.update("Avacado Toast recipe");
    }
}
