package org.tpokora;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "heloo")
public class HelloView extends VerticalLayout {

    public HelloView() {
        Button configBtn = new Button("Config");
        configBtn.addClickListener(e -> {
            configBtn.getUI().ifPresent(ui -> ui.navigate("config"));
        });
        add(configBtn);
    }
}
