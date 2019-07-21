package org.tpokora.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.views.common.RouteStrings;

@Tag("home-view")
@Route(value = RouteStrings.HOME_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.HOME)
public class HomeView extends Div {

    public HomeView() {
        setText("HOME");
    }
}
