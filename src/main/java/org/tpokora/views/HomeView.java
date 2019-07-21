package org.tpokora.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.views.common.RouteStrings;

@Tag("home-view")
@Route(value = RouteStrings.HOME_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.HOME)
public class HomeView extends Div {

    public static final String WELCOME_TO_EMMY_APP = "Welcome to Emmy App!";
    private Div homeContent;

    public HomeView() {
        this.homeContent = new Div();
        setupContent();
        add(homeContent);
    }

    private void setupContent() {
        this.homeContent.getStyle().set("padding", "30px");
        this.homeContent.add(new H3(WELCOME_TO_EMMY_APP));
    }
}
