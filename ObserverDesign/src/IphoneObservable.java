import java.util.ArrayList;
import java.util.List;

public class IphoneObservable implements IObservable{

    List<IObserver> observerList;
    int stock=0;

    public IphoneObservable(){
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
            observer.update(this.getClass().getSimpleName().replace("Observable","")+" is back in stock");
        }
    }

    @Override
    public void update(Object item) {
            stock += (int)item;
            this.notifyObserver();
    }

}
