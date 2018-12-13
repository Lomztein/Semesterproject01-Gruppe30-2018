package depressionsspillet.worldofzuul.observables;

public interface Observer<T extends Event> {
    
    void onNotify (T event);
    
}
