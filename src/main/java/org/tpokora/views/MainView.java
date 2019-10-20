package org.tpokora.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Span;
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
    private AppLayout appLayout;

    public MainView() {
        this.appLayout = new AppLayout();
        addToContent(appLayout);
        createNavbar();
    }

    private void createNavbar() {
        Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);

        Span appName = new Span(EMMY_APP);
        appLayout.addToNavbar(true, appName, tabs);

        tabs.add(createTab(HomeView.class, RouteStrings.HOME));
        tabs.add(createTab(UsersView.class, RouteStrings.USERS));
        tabs.add(createTab(ConfigView.class, RouteStrings.CONFIG));
        tabs.add(createTab(WeatherView.class, RouteStrings.WEATHER));
    }

    private Tab createTab(Class<? extends Component> navigationTarget, String linkText) {
        RouterLink routerLink = new RouterLink(linkText, navigationTarget);
        Tab tab = new Tab();
        tab.add(routerLink);
        tab.setId(linkText.toLowerCase() + "-tab");
        return tab;
    }


}
