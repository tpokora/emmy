package org.tpokora.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Tag("home-view")
@Route(value = "home", layout = MainView.class)
@PageTitle("Home")
public class HomeView extends Div {

    public HomeView() {
        setText("HOME");
    }
}
