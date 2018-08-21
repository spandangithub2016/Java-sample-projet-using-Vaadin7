package com.stpl.trainee.assignment6.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Theme("mytheme")
public class MyUI extends UI {

    private static final long serialVersionUID = 1L;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	

        Navigator navigator = new Navigator(this, this);

        // Login
        navigator.addView("Login", new Login());

        // SignUp
        navigator.addView("SignUp", new SignUp());

        // Delete
        navigator.addView("Delete", new Delete());

        // AdminHome
        navigator.addView("AdminHome", new AdminHome());

        // UserHome
        navigator.addView("UserHome", new UserHome());

        // ViewInput
        navigator.addView("ViewInput", new ViewAndUpdate());

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    	
        private static final long serialVersionUID = 1L;
    }
}
