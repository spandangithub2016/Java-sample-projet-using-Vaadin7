package com.stpl.trainee.assignment6.ui;

import java.sql.SQLException;

import com.stpl.trainee.assignment6.service.LoginService;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Login extends VerticalLayout implements View {
	
    private static final long serialVersionUID = 1L;

    private LoginService loginService = new LoginService();

    private TextField username = new TextField("Enter Username");
    private PasswordField password = new PasswordField("Enter Password");

    private Button login = new Button("Login");;
    private Button signUp = new Button("Sign Up");
    
    public void login()
    {
    	
    }
    
    public Login() {
    	
    	login();

        HorizontalLayout hLayout = new HorizontalLayout();

        login.addStyleName("friendly");
        signUp.addStyleName("primary");

        hLayout.addComponent(login);
        hLayout.addComponent(signUp);

        login.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;
            
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if ("admin".equals(username.getValue()) && "admin".equals(password.getValue())) {

                    getUI().getNavigator().navigateTo("AdminHome");

                } else
					try {
						if (loginService.login(username.getValue(), password.getValue())) {

						    getUI().getNavigator().navigateTo("UserHome");

						    username.setValue("");
						    password.setValue("");

						} else {

						    Notification.show("Incorrect Login Details!", Notification.Type.ERROR_MESSAGE);
						}
					} catch (ReadOnlyException | SQLException e) {
						e.printStackTrace();
					}
            }

        });

        signUp.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().navigateTo("SignUp");

                username.setValue("");
                password.setValue("");
            }
        });

        hLayout.setSpacing(true);

        FormLayout formLayout = new FormLayout(username, password, hLayout);
        formLayout.setMargin(true);

        Panel loginPanel = new Panel("Student management System", formLayout);
        loginPanel.setWidth("450");
        loginPanel.setHeight("250");

        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        setHeight("100%");

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
