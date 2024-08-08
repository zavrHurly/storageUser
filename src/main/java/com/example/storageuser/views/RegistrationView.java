package com.example.storageuser.views;

import com.example.storageuser.component.RegistrationForm;
import com.example.storageuser.component.RegistrationFormBinder;
import com.example.storageuser.service.UserService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/registration")
@AnonymousAllowed
public class RegistrationView extends VerticalLayout {

    @Autowired
    public RegistrationView(UserService service) {
        RegistrationForm registrationForm = new RegistrationForm();
        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);

        add(registrationForm);

        RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registrationForm, service);
        registrationFormBinder.addBindingAndValidation();
    }
}
