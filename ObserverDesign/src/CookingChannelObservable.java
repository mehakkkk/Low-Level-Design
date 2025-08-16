import java.util.ArrayList;
import java.util.List;

public class CookingChannelObservable implements IObservable{
    List<IObserver> observerList;
    String videoTitle;

    public CookingChannelObservable(){
        observerList = new ArrayList<>();
    }

    @Override
    public void register(IObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void unregister(IObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (IObserver observer:observerList)
        {
            observer.update(this.getClass().getSimpleName().replace("Observable","")+" uploaded a new video "+videoTitle);
        }
    }

    @Override
    public void update(Object item) {
        videoTitle = (String) item;
        this.notifyObserver();
    }

}
