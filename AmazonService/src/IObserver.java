public interface IObserver {
    public void subscribe(Object obj);
    public void unsubscribe(Object obj);
    public void notify(Object obj);
}
