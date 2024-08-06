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
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/user")
@PermitAll
public class UserView extends VerticalLayout {
    private final UserService userService;

    private final UserEditor userEditor;

    private Grid<User> userGrid= new Grid<>(User.class);
    private final TextField filter = new TextField();
    private final HorizontalLayout toolbar = new HorizontalLayout(filter);

    @Autowired
    public UserView(UserService userService, UserEditor userEditor) {
        this.userService = userService;
        this.userEditor = userEditor;

        filter.setPlaceholder("Type to filter");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> fillList(field.getValue()));

        add(toolbar, userGrid);

        userGrid
                .asSingleSelect()
                .addValueChangeListener(e -> userEditor.editUser(e.getValue()));

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
