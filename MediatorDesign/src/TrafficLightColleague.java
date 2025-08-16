public class TrafficLightColleague implements IColleague{

    private IMediator trafficMediator;
    private int id;

    public TrafficLightColleague(int id,IMediator trafficMediator)
    {
        this.id = id;
        this.trafficMediator = trafficMediator;
        trafficMediator.addColleague(this);
    }

    @Override
    public void recieveMessage(String message) {
        System.out.println("Traffic light "+id+" recieved message "+message);
    }

    @Override
    public void sendMessage(String message) {
        trafficMediator.notifyColleague(message);
    }
}
