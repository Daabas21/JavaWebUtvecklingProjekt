package com.example.javawebutvecklingprojekt.security;

import com.example.javawebutvecklingprojekt.views.TodoManager;
import com.example.javawebutvecklingprojekt.views.UserView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class PrincipalUtils {

    public static String getName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static boolean isAuthenticated(){
        return !SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()
                .equalsIgnoreCase("anonymousUser");
    }

    public static void logout(){
        new SecurityContextLogoutHandler().logout(VaadinServletRequest.getCurrent().getHttpServletRequest(), null, null);
        UI.getCurrent().navigate(UserView.class);
    }

}
