package com.example.storageuser.component;

import com.example.storageuser.domain.User;
import com.example.storageuser.service.UserService;
import com.example.storageuser.web.UserDto;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;

public class RegistrationFormBinder {

    private final RegistrationForm registrationForm;

    private boolean enablePasswordValidation;

    private final UserService service;

    public RegistrationFormBinder(RegistrationForm registrationForm, UserService service) {
        this.registrationForm = registrationForm;
        this.service = service;
    }

    public void addBindingAndValidation() {
        BeanValidationBinder<UserDto> binder = new BeanValidationBinder<>(UserDto.class);
        binder.bindInstanceFields(registrationForm);

        binder.forField(registrationForm.getPasswordField())
                .withValidator(this::passwordValidator).bind("password");

        registrationForm.getPasswordConfirmField().addValueChangeListener(e -> {
            enablePasswordValidation = true;

            binder.validate();
        });
        binder.setStatusLabel(registrationForm.getErrorMessageField());
        registrationForm.getSubmitButton().addClickListener(event -> {
            try {
                UserDto userBean = new UserDto();
                binder.writeBean(userBean);
                service.create(new User(userBean));
                showSuccess(userBean);
            } catch (ValidationException exception) {
            }
        });
    }
    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {

        if (pass1 == null || pass1.length() < 8) {
            return ValidationResult.error("Password should be at least 8 characters long");
        }

        if (!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }

        String pass2 = registrationForm.getPasswordConfirmField().getValue();

        if (pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }

        return ValidationResult.error("Passwords do not match");
    }

    private void showSuccess(UserDto userBean) {
        Notification notification =
                Notification.show("Data saved, welcome " + userBean.getUsername());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

}
