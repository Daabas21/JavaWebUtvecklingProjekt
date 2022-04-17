package com.example.javawebutvecklingprojekt.views;

import com.example.javawebutvecklingprojekt.security.PrincipalUtils;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@Route(value = "/user", layout = AppView.class)
public class UserView extends Div {

    public UserView(){
        add(new H1("Welcome user"));
        add(new H3("Name: "));
        add(new H3("email: "));
        add(new H3("passwor: "));

    }
}
