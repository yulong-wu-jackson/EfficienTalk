package interface_adapter.connect;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ConnectViewModel extends ViewModel {
    public final String TITLE_LABEL = "Connect View";
    public final String IP_LABEL = "IP";
    public final String PORT_LABEL = "Port";

    public static final String CONNECT_BUTTON_LABEL = "Link Start";

    private ConnectState state = new ConnectState();
    public ConnectViewModel() {super("connect");}

    public void setState(ConnectState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ConnectState getState() {
        return state;
    }
}

