package com.stpl.trainee.assignment6.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class UserHome extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;

    public UserHome() {
        Label lblTitle;
        Label lblHeader;

        Button update;
        Button logout;

        setSpacing(true);
        setMargin(true);

        lblTitle = new Label("Welcome User");
        lblTitle.addStyleName("h1");
        addComponent(lblTitle);

        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(true);
        formLayout.setWidth("700");
        formLayout.addStyleName("light");
        addComponent(formLayout);

        lblHeader = new Label("User Home");
        lblHeader.addStyleName("h2");
        lblHeader.addStyleName("colored");
        formLayout.addComponent(lblHeader);

        update = new Button("Edit & Save");
        update.addStyleName("friendly");

        update.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                getUI().getNavigator().navigateTo("ViewInput");
            }
        });

        logout = new Button("Log Out");
        logout.addStyleName("primary");

        logout.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                getUI().getNavigator().navigateTo("");
            }

        });

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, true, true, true));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.addComponent(footer);
        footer.addComponent(update);
        footer.addComponent(logout);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
