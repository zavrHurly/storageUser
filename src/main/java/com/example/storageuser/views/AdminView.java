package com.example.storageuser.views;

import com.example.storageuser.component.UserEditor;
import com.example.storageuser.domain.User;
import com.example.storageuser.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/admin", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {

    private final UserService userService;

    private Grid<User> userGrid= new Grid<>(User.class);
    private final TextField filter = new TextField();
    private final Button addNewButton = new Button("New user", VaadinIcon.PLUS.create());
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewButton);

    @Autowired
    public AdminView(UserService userService, UserEditor userEditor) {
        this.userService = userService;

        filter.setPlaceholder("Type to filter");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> fillList(field.getValue()));

        add(toolbar, userGrid, userEditor);

        userGrid
                .asSingleSelect()
                .addValueChangeListener(e -> userEditor.editUser(e.getValue()));

        addNewButton.addClickListener(e -> {
            userEditor.setVisible(true);
            userEditor.editUser(new User());
        });

        userEditor.setChangeHandler(() -> {
            userEditor.setVisible(true);
            fillList(filter.getValue());
        });
        fillList("");
    }

    private void fillList(String name) {
        if (name.isEmpty()) {
            userGrid.setItems(this.userService.getAll());
        } else {
            userGrid.setItems(this.userService.findByUsername(name));
        }
    }
}
