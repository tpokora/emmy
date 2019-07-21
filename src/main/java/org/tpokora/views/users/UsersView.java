package org.tpokora.views.users;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.views.AbstractView;
import org.tpokora.views.MainView;
import org.tpokora.views.common.RouteStrings;

@Tag("users-view")
@Route(value = RouteStrings.USERS_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.USERS)
public class UsersView extends AbstractView {

    public UsersView() {
        setupContentDefaultStyles();
        addToContent(new H3(RouteStrings.USERS));
    }
}
