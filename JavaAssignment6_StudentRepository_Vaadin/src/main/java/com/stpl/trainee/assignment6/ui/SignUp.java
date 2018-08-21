
package com.stpl.trainee.assignment6.ui;

import java.sql.SQLException;

import com.stpl.trainee.assignment6.bean.StudentBean;
import com.stpl.trainee.assignment6.service.SignUpService;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
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

public class SignUp extends VerticalLayout implements View {
	
	private static final long serialVersionUID = 1L;

	 private StudentBean studentBean = new StudentBean();
	 private SignUpService signUpService = new SignUpService();

	 private Label lblTitle;
	 private Label lblHeader;

    private TextField fullname;
    private TextField username;
    private PasswordField password;
    private TextField address;
    private DateField dob;
    private OptionGroup gender;
    private TextField email;
    private TextField contact;

    private Button confirm;
    private Button cancel;

    public SignUp() {

        setSpacing(true);
        setMargin(true);

        lblTitle = new Label("Sign Up Form");
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

        fullname = new TextField("Fullname");
        fullname.setRequired(true);
        formLayout.addComponent(fullname);

        username = new TextField("UserName");
        username.setRequired(true);
        formLayout.addComponent(username);

        password = new PasswordField("Password");
        password.setRequired(true);
        formLayout.addComponent(password);

        address = new TextField("Address");
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
        gender.addStyleName("horizontal");
        formLayout.addComponent(gender);

        email = new TextField("Email ID");
        email.setRequired(true);
        formLayout.addComponent(email);

        contact = new TextField("contact");
        formLayout.addComponent(contact);

        confirm = new Button("Confirm");
        confirm.addStyleName("primary");

        confirm.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                studentBean.setFullName(fullname.getValue());
                studentBean.setUserName(username.getValue());
                studentBean.setPassword(password.getValue());
                studentBean.setAddress(address.getValue());
                studentBean.setDob((String) dob.getData());
                studentBean.setGender((String) gender.getValue());
                studentBean.setEmail(email.getValue());
                studentBean.setContact(contact.getValue());

                
					try {
						if (signUpService.signUp(studentBean)) {

						    fullname.setValue("");
						    username.setValue("");
						    password.setValue("");
						    address.setValue("");
						    gender.clear();
						    email.setValue("");
						    contact.setValue("");

						    Notification.show("You signed up successfully!");

						} else {

						    getUI().getNavigator().navigateTo("SignUp");
						    Notification.show("Registration Falied, Please Try Again!");

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

                getUI().getNavigator().navigateTo("");
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
