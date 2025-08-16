public class Main {
    public static void main(String[] args) {
        IMediator trafficMediator = new TrafficMediator();

        IColleague light1 = new TrafficLightColleague(1,trafficMediator);
        IColleague light2 = new TrafficLightColleague(2,trafficMediator);
        IColleague light3 = new TrafficLightColleague(3,trafficMediator);
        IColleague light4 = new TrafficLightColleague(4,trafficMediator);

        light1.sendMessage("Change to red");
        light1.sendMessage("Change to green");
    }
}
