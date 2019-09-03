package org.tpokora.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import org.tpokora.views.common.RouteStrings;
import org.tpokora.views.users.UsersView;
import org.tpokora.views.weather.WeatherView;

import static javax.swing.ScrollPaneConstants.VIEWPORT;

@Viewport(VIEWPORT)
public class MainView extends AbstractView implements RouterLayout {

    public static final String EMMY_APP = "EmmyApp";

    public MainView() {
        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        AppLayout appLayout = new AppLayout();
        appLayout.addToNavbar(true, tabs);

        tabs.add(createTab(HomeView.class, RouteStrings.HOME));
        tabs.add(createTab(UsersView.class, RouteStrings.USERS));
        tabs.add(createTab(ConfigView.class, RouteStrings.CONFIG));
        tabs.add(createTab(WeatherView.class, RouteStrings.WEATHER));

        addToContent(appLayout);
    }

    private Tab createTab(Class<? extends Component> navigationTarget, String linkText) {
        RouterLink routerLink = new RouterLink(linkText, navigationTarget);
        Tab tab = new Tab();
        tab.add(routerLink);
        return tab;
    }


}
