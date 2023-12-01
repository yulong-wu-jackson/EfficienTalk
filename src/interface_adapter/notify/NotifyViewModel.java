package interface_adapter.notify;
import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NotifyViewModel extends ViewModel{
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private NotifyState state = new NotifyState();


    public NotifyViewModel() {
        super("notify");
    }


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public NotifyState getState() {
        return state;
    }

    public void setState(NotifyState state) {
        this.state = state;
    }
}


