package org.tpokora.views.users;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.views.MainView;
import org.tpokora.views.common.RouteStrings;

@Tag("users-view")
@Route(value = RouteStrings.USERS_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.USERS)
public class UsersView extends Div {

    public UsersView() {
        setText(RouteStrings.USERS);
    }
}
