package org.tpokora.views;

import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.page.Viewport;
import org.tpokora.views.common.RouteStrings;

import static javax.swing.ScrollPaneConstants.VIEWPORT;

@Viewport(VIEWPORT)
public class MainView extends AbstractAppRouterLayout {

    public static final String WELCOME_TO_EMMY_APP = "Welcome to Emmy App!";

    public MainView() {

    }

    @Override
    protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
        appLayout.setBranding(new H3(WELCOME_TO_EMMY_APP));

        appLayoutMenu.addMenuItems(new AppLayoutMenuItem(RouteStrings.HOME, RouteStrings.HOME_ROUTE),
                new AppLayoutMenuItem(RouteStrings.USERS, RouteStrings.USERS_ROUTE),
                new AppLayoutMenuItem(RouteStrings.CONFIG, RouteStrings.CONFIG_ROUTE)
        );

    }
}
