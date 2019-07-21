package org.tpokora.views.users;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.users.model.User;
import org.tpokora.users.services.UserDetailsServiceImpl;
import org.tpokora.views.AbstractView;
import org.tpokora.views.MainView;
import org.tpokora.views.common.RouteStrings;

import java.util.List;

@Tag("users-view")
@Route(value = RouteStrings.USERS_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.USERS)
public class UsersView extends AbstractView {

    VerticalLayout verticalLayout = new VerticalLayout();
    List<User> userList;

    public UsersView(UserDetailsServiceImpl userDetailsService) {
        this.verticalLayout.add(new H3(RouteStrings.USERS));
        this.userList = userDetailsService.gettAllUsers();

        for (User user : this.userList) {
            VerticalLayout userDetails = new VerticalLayout();
            userDetails.add(
                    new Span(String.format("Email: %s", user.getEmail())),
                    new Span(String.format("Role: %s", user.getRoles().stream().findFirst().get().getName())));
            Details userDetailsElement = new Details(user.getUsername(),
                    userDetails);
//        show notification
//        userDetailsElement.addOpenedChangeListener(e ->
//                Notification.show(e.isOpened() ? "Opened" : "Closed"));

            this.verticalLayout.add(userDetailsElement);
        }

        setupContentDefaultStyles();
        addToContent(this.verticalLayout);
    }
}
