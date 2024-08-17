package com.example.storageuser.views;

import com.example.storageuser.component.UserEditor;
import com.example.storageuser.domain.User;
import com.example.storageuser.service.UserService;
import com.example.storageuser.web.AuthUser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/token")
@PermitAll
public class TokenView extends VerticalLayout {

    @Autowired
    AuthUser authenticatedUser;

    private Button sayHello;

    public TokenView() {
        VaadinSession.getCurrent().setAttribute("foo", new Object());

        sayHello = new Button("Say hello!");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + authenticatedUser.getAuthentication().toString());
        });

        add(sayHello);
    }

}
