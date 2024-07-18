package com.example.storageuser.component;

import com.example.storageuser.domain.User;
import com.example.storageuser.service.UserService;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout implements KeyNotifier {
    private final UserService userService;

    private User user = new User();

    private TextField surname = new TextField("", "Surname");
    private TextField name = new TextField("", "Name");
    private TextField fatherName = new TextField("", "FatherName");
    private TextField email = new TextField("", "email");
    private TextField callNumber = new TextField("", "callNumber");
    private TextField password = new TextField("", "password");

    private Button delete = new Button("Delete");
    private Button save = new Button("Save");
    private Button cancel = new Button("Cancel");

    private HorizontalLayout buttons = new HorizontalLayout(save, delete, cancel);

    private Binder<User> binder = new Binder<>(User.class);
    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler {
        void onChange();
    }

    @Autowired
    public UserEditor(UserService userService) {
        this.userService = userService;

        add(name, surname, fatherName, email, callNumber, password, buttons);

        binder.bindInstanceFields(this);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        delete.addClickListener(e -> {
            userService.delete(user);
            changeHandler.onChange();
        });
        save.addClickListener(e -> {
            try {
                binder.writeBean(user);
            } catch (ValidationException f) {
            }
            if(user.getId() == null) {
                userService.create(user);
            } else{
                userService.update(user);
            }
            changeHandler.onChange();
            setVisible(false);
            name.clear();
            surname.clear();
            fatherName.clear();
            email.clear();
            callNumber.clear();
            password.clear();
        });
        cancel.addClickListener(e -> {
            editUser(user);
            setVisible(false);
        });
    }


    public void editUser(User user) {
        if (user == null) {
            setVisible(false);
            return;
        }

        if (user.getId() != null) {
            this.user = userService.findById(user.getId());
        } else {
            this.user = user;
        }

        binder.setBean(this.user);

        setVisible(true);

        name.focus();
    }
}
