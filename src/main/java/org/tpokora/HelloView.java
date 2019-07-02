package org.tpokora;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class HelloView extends VerticalLayout {

    public HelloView() {
        add(new Button("Click me", event -> Notification.show("Hello to Emmy application!")));
    }
}
