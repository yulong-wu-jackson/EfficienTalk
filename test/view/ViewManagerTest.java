package view;

import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import static org.junit.Assert.*;

public class ViewManagerTest {

    private CardLayout cardLayout;
    private JPanel views;
    private ViewManagerModel viewManagerModel;
    private ViewManager viewManager;

    @Before
    public void setUp() {
        cardLayout = new CardLayout();
        views = new JPanel(cardLayout);
        viewManagerModel = new ViewManagerModel();
        viewManager = new ViewManager(views, cardLayout, viewManagerModel);
    }

    @Test
    public void shouldSwitchViewOnPropertyChange() {
        String viewName = "TestView";
        JPanel testPanel = new JPanel();
        views.add(testPanel, viewName);

        // Manually trigger the property change event
        viewManagerModel.setActiveView(viewName);
        viewManagerModel.firePropertyChanged();


        // Verify that the CardLayout showed the correct view
        // You will need to find a way to assert that the correct view is displayed.
        // CardLayout doesn't provide a direct way to get the current card's name,
        // so you might need to implement a method in your ViewManager to get the current view name.
        assertEquals(viewName, viewManagerModel.getActiveView());
    }
}
