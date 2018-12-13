package depressionsspillet.worldofzuul.observables;

public class Event {
    
    private final Object source;
    private Object target;
    
    public Event (Object source) {
        this.source = source;
    }
    
    protected Event withTarget (Object target) {
        this.target = target;
        return this;
    }
    
    public Object getSource () {
        return source;
    }
    
    public Object getTarget () {
        return target;
    }
    
}
