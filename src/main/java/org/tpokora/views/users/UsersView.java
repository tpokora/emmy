package org.tpokora.views.users;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.config.model.Property;
import org.tpokora.users.model.User;
import org.tpokora.users.services.UserDetailsServiceImpl;
import org.tpokora.views.AbstractView;
import org.tpokora.views.MainView;
import org.tpokora.views.common.RouteStrings;

import java.util.ArrayList;
import java.util.List;

@Tag("users-view")
@Route(value = RouteStrings.USERS_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.USERS)
public class UsersView extends AbstractView {

    private UserDetailsServiceImpl userDetailsService;
    private VerticalLayout verticalLayout = new VerticalLayout();
    private Grid<UserRow> grid;


    public UsersView(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.verticalLayout.add(new H3(RouteStrings.USERS));
        setupUsersGrid();
        this.verticalLayout.add(this.grid);

        setupContentDefaultStyles();
        addToContent(this.verticalLayout);
    }

    private void setupUsersGrid() {
        this.grid = new Grid<>(UserRow.class);
        this.grid.setColumns("username", "email", "role");
        this.grid.setItems(getUserRows(this.userDetailsService.gettAllUsers()));
    }

    private ArrayList<UserRow> getUserRows(List<User> userList) {
        ArrayList<UserRow> userRows = new ArrayList<>();
        for (User user : userList) {
            userRows.add(new UserRow(user.getUsername(), user.getEmail(), user.getRoles().stream().findFirst().get().getName()));
        }
        return userRows;
    }
}
