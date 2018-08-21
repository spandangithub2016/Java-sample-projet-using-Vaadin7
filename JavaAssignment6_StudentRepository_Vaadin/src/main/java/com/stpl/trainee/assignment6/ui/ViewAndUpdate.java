package com.stpl.trainee.assignment6.ui;

import java.sql.SQLException;
import com.stpl.trainee.assignment6.bean.StudentBean;
import com.stpl.trainee.assignment6.service.UpdateService;
import com.stpl.trainee.assignment6.service.ViewService;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class ViewAndUpdate extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    
    ViewService viewService = new ViewService();
    UpdateService updateService = new UpdateService();

    Label lblTitle;
    Label lblHeader;

    TextField usernameInput;

    TextField fullname;
    TextField username;
    PasswordField password;
    TextField address;
    DateField dob;
    OptionGroup gender;
    TextField email;
    TextField contact;

    Button search;
    Button cancel;
    Button save;

    public void update(final StudentBean bean) {

        setSpacing(true);
        setMargin(true);

        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(true);
        formLayout.setWidth("700");
        formLayout.addStyleName("light");
        addComponent(formLayout);

        lblHeader = new Label("View & Update Information");
        lblHeader.addStyleName("h2");
        lblHeader.addStyleName("colored");
        formLayout.addComponent(lblHeader);

        fullname = new TextField("Fullname");
        fullname.setValue(bean.getFullName());
        fullname.setRequired(true);
        formLayout.addComponent(fullname);

        username = new TextField("UserName");
        username.setValue(bean.getUserName());
        username.setRequired(true);
        formLayout.addComponent(username);

        password = new PasswordField("Password");
        password.setValue(bean.getPassword());
        password.setRequired(true);
        formLayout.addComponent(password);

        address = new TextField("Address");
        address.setValue(bean.getAddress());
        address.setRequired(true);
        formLayout.addComponent(address);

        dob = new DateField("Date of Birth");
        dob.setRequired(true);
        dob.setDateFormat("dd-MM-yyyy");
        dob.setValue(new java.util.Date());
        formLayout.addComponent(dob);

        gender = new OptionGroup("Gender");
        gender.addItem("Male");
        gender.addItem("Female");
        gender.setValue(bean.getGender());
        gender.addStyleName("horizontal");
        formLayout.addComponent(gender);

        email = new TextField("Email ID");
        email.setValue(bean.getEmail());
        email.setRequired(true);
        formLayout.addComponent(email);

        contact = new TextField("contact");
        contact.setValue(bean.getContact());
        formLayout.addComponent(contact);

        save = new Button("Confirm & Save");
        save.addStyleName("primary");

        save.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
            	
            	bean.setFullName(fullname.getValue());
            	bean.setUserName(username.getValue());
            	bean.setPassword(password.getValue());
            	bean.setAddress(address.getValue());
            	bean.setDob((String) dob.getData());
            	bean.setGender((String) gender.getValue());
            	bean.setEmail(email.getValue());
            	bean.setContact(contact.getValue());
            	
                try {
					if (updateService.update(bean) != 0) {
						Notification.show("Student Details Are Updated Successfully!");
					    usernameInput.setValue("");
					    fullname.setValue("");
					    username.setValue("");
					    password.setValue("");
					    address.setValue("");
					    dob.clear();
					    gender.clear();
					    email.setValue("");
					    contact.setValue("");

					    Page.getCurrent().reload();

					} else {
					    Notification.show("Updation Falied, Please Try Again!");
					}
				} catch (ReadOnlyException | SQLException e) {
					e.printStackTrace();
				}
            }
            
        });

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, true, true, true));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.addComponent(footer);
        footer.addComponent(save);

    }

    public ViewAndUpdate() {

        setSpacing(true);
        setMargin(true);

        lblTitle = new Label("View & Update Form");
        lblTitle.addStyleName("h1");
        addComponent(lblTitle);

        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(true);
        formLayout.setWidth("700");
        formLayout.addStyleName("light");
        addComponent(formLayout);

        lblHeader = new Label("Search to View Information");
        lblHeader.addStyleName("h2");
        lblHeader.addStyleName("colored");
        formLayout.addComponent(lblHeader);

        usernameInput = new TextField("UserName");
        usernameInput.setRequired(true);
        formLayout.addComponent(usernameInput);

        search = new Button("Search");
        search.addStyleName("primary");

        search.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

            	StudentBean studentBean = new StudentBean();
                studentBean.setUserName(usernameInput.getValue());

               try{ 
            	   
            	   studentBean = viewService.view(studentBean);
                    if (studentBean.getFullName() != null) {

                    	Notification.show(studentBean.getFullName());
                        update(studentBean);

                    } else {

                        Notification.show("Wrong Username, Please Try Again!");

                    }
                } catch (SQLException e) {
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
                getUI().getNavigator().navigateTo("UserHome");
            }

        });

        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, true, true, true));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        formLayout.addComponent(footer);
        footer.addComponent(search);
        footer.addComponent(cancel);

    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
