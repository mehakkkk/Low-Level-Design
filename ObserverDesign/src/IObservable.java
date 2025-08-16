public interface IObservable<T> {

    public void register(IObserver<T> observer);
    public void unregister(IObserver<T> observer);
    public void notifyObserver();
    public void update(T item);
}
