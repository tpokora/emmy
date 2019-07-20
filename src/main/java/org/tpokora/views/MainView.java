package org.tpokora.views;

import com.vaadin.flow.component.applayout.AbstractAppRouterLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.page.Viewport;

import static javax.swing.ScrollPaneConstants.VIEWPORT;

@Viewport(VIEWPORT)
public class MainView extends AbstractAppRouterLayout {

    public static final String WELCOME_TO_EMMY_APP = "Welcome to Emmy App!";
    public static final String CONFIG_BTN_TEXT = "Config";
    public static final String ADD_USER_BTN_TEXT = "Create User";

    public MainView() {

    }

    @Override
    protected void configure(AppLayout appLayout, AppLayoutMenu appLayoutMenu) {
        appLayout.setBranding(new H3(WELCOME_TO_EMMY_APP));

        appLayoutMenu.addMenuItems(new AppLayoutMenuItem("HOME", "home"),
                new AppLayoutMenuItem(ADD_USER_BTN_TEXT, "signup"),
                new AppLayoutMenuItem(CONFIG_BTN_TEXT, "config")
        );

    }
}
