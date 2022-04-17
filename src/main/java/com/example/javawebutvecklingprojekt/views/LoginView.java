package com.example.javawebutvecklingprojekt.views;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "/login" , layout = AppView.class)
public class LoginView extends Div implements BeforeEnterObserver {

    LoginForm loginForm = new LoginForm();

    public LoginView() {

        setHeight(50, Unit.EM);

        getStyle().set("background-color", "var(--lumo-contrast-5pct)")
                .set("display", "flex")
                .set("justify-content", "center")
                .set("margin-top", "var(--lumo-space-2.5)")
                .set("padding", "var(--lumo-space-1)");

        loginForm.setAction("login");

        add(loginForm);

        loginForm.getElement().setAttribute("no-autofocus", "");


    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (beforeEnterEvent
                .getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
        }
    }
}
