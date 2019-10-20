package org.tpokora.views.users;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.users.model.User;
import org.tpokora.users.services.UserDetailsServiceImpl;
import org.tpokora.views.AbstractView;
import org.tpokora.views.MainView;
import org.tpokora.views.common.RouteStrings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        this.grid = new Grid<>();
        this.grid.setSelectionMode(Grid.SelectionMode.MULTI);
        this.grid.setItems(getUserRows(this.userDetailsService.getAllUsers()));
        Grid.Column<UserRow> usernameColumn = this.grid.addColumn(UserRow::getUsername).setHeader("Username");
        Grid.Column<UserRow> emailColumn = this.grid.addColumn(UserRow::getEmail).setHeader("Email");
        Grid.Column<UserRow> roleColumn = this.grid.addColumn(UserRow::getRole).setHeader("Role");
    }

    private ArrayList<UserRow> getUserRows(List<User> userList) {
        ArrayList<UserRow> userRows = (ArrayList<UserRow>) userList.stream()
                .map(user ->
                        new UserRow(user.getUsername(),
                                user.getEmail(),
                                user.getRoles().stream().findFirst().get().getName()))
                .collect(Collectors.toList());

        return userRows;
    }
}
