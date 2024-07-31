package com.example.storageuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
public class StorageUserApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(StorageUserApplication.class, args);
    }
}
