package com.stpl.trainee.assignment6.ui;

import java.sql.SQLException;

import com.stpl.trainee.assignment6.bean.StudentBean;
import com.stpl.trainee.assignment6.service.DeleteService;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Delete extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    
    private StudentBean studentBean = new StudentBean();
    private DeleteService deleteService = new DeleteService();

    private Label lblTitle;
    private Label lblHeader;

    private TextField username;

    private Button confirm;
    private Button cancel;

    public Delete() {

        setSpacing(true);
        setMargin(true);

        lblTitle = new Label("Delete Form");
        lblTitle.addStyleName("h1");
        addComponent(lblTitle);

        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(true);
        formLayout.setWidth("700");
        formLayout.addStyleName("light");
        addComponent(formLayout);

        lblHeader = new Label("Personal Information");
        lblHeader.addStyleName("h2");
        lblHeader.addStyleName("colored");
        formLayout.addComponent(lblHeader);

        username = new TextField("UserName");
        username.setRequired(true);
        formLayout.addComponent(username);

        confirm = new Button("Confirm");
        confirm.addStyleName("primary");

        confirm.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                studentBean.setUserName(username.getValue());

                try {
					if (deleteService.delete(studentBean) != 0) {

					    username.setValue("");

					    getUI().getNavigator().navigateTo("AdminHome");
					    Notification.show("Student Records Are Deleted successfully!");

					} else {

					    getUI().getNavigator().navigateTo("Delete");
					    Notification.show("Wrong Username, Please Try Again!");

					}
				} catch (ReadOnlyException | SQLException e) {
					e.printStackTrace();
				}
            }
        });

        cancel = new Button("Cancel");
        cancel.addStyleName("danger");

        cancel.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                getUI().getNavigator().navigateTo("AdminHome");
            }

        });

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, true, true, true));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.addComponent(footer);
        footer.addComponent(confirm);
        footer.addComponent(cancel);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
