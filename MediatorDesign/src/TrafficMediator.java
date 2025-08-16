import java.util.ArrayList;
import java.util.List;

public class TrafficMediator implements IMediator{
    List<IColleague> trafficLights;

    public TrafficMediator(){
        trafficLights = new ArrayList<>();
    }

    @Override
    public void addColleague(IColleague colleague) {
        trafficLights.add(colleague);
    }

    @Override
    public void notifyColleague(String message) {
        for (IColleague light:trafficLights)
        {
            light.recieveMessage(message);
        }
    }
}
