package com.example.javawebutvecklingprojekt.views;

import com.example.javawebutvecklingprojekt.security.PrincipalUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


public class AppView extends AppLayout {

    public AppView(){

        Button toggleButton = new Button("Toggle theme", click -> {
            getElement().executeJs("document.querySelector('html').hasAttribute('theme') && document.querySelector('html').getAttribute('theme').includes('dark') ? document.querySelector('html').setAttribute('theme',document.querySelector('html').getAttribute('theme').replaceAll('dark','')) : document.querySelector('html').setAttribute('theme', 'dark');");
        });

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        H2 navBarTitle = new H2("Todo");
        DrawerToggle drawerToggle = new DrawerToggle();

        Div toggle = new Div(drawerToggle, toggleButton);
        toggle.getStyle().set("display", "flex");

        Div userDiv = new Div();
        horizontalLayout.add(toggle, navBarTitle, userDiv);

        Button loginButton = new Button("Login", e -> UI.getCurrent().navigate(LoginView.class));
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button logoutButton = new Button("Logout", e -> PrincipalUtils.logout());
        logoutButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        if (PrincipalUtils.isAuthenticated())
            Notification.show(PrincipalUtils.getName());

        userDiv.add(PrincipalUtils.isAuthenticated() ? logoutButton : loginButton);


        horizontalLayout.setSizeFull();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        addToNavbar(horizontalLayout);

        //Todo router styling
        RouterLink todoViewLink = new RouterLink("Todos", TodoManager.class);
        RouterLink userViewLink = new RouterLink("User", UserView.class);

        addToDrawer(new VerticalLayout(todoViewLink, userViewLink));
        drawerToggle.addThemeVariants();
    }

}
