package com.example.storageuser.views;

import com.example.storageuser.component.UserEditor;
import com.example.storageuser.domain.User;
import com.example.storageuser.service.UserService;
import com.example.storageuser.web.UserDtoToView;
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

@Route(value="/user", layout = MainLayout.class)
@PermitAll
public class UserView extends VerticalLayout {

    private final UserService userService;

    private Grid<UserDtoToView> userGrid= new Grid<>(UserDtoToView.class);
    private final TextField filter = new TextField();
    private final HorizontalLayout toolbar = new HorizontalLayout(filter);

    @Autowired
    public UserView(UserService userService, UserEditor userEditor) {
        this.userService = userService;

        filter.setPlaceholder("Type to filter");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(field -> fillList(field.getValue()));

        add(toolbar, userGrid);

        userGrid
                .asSingleSelect()
                .addValueChangeListener(e -> userEditor.editDtoUser(e.getValue()));

        userEditor.setChangeHandler(() -> {
            userEditor.setVisible(true);
            fillList(filter.getValue());
        });
        fillList("");
    }

    private void fillList(String name) {
        if (name.isEmpty()) {
            userGrid.setItems(this.userService.getAllDto());
        } else {
            userGrid.setItems(this.userService.findDtoByUsername(name));
        }
    }
}
