package com.stpl.trainee.assignment6.ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.stpl.trainee.assignment6.connection.MyConnect;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AdminHome extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    Label lblTitle;
    Label lblHeader;
    Label lblHeader1;

    Button delete;
    Button refresh;
    Button logout;

    private int height = 50;

    public AdminHome() {
    	
        setSpacing(true);
        setMargin(true);

        lblTitle = new Label("Welcome Admin");
        lblTitle.addStyleName("h1");
        addComponent(lblTitle);

        FormLayout formLayout = new FormLayout();
        formLayout.setMargin(true);
        formLayout.setWidth("700");
        formLayout.addStyleName("light");
        addComponent(formLayout);

        lblHeader = new Label("Admin Home");
        lblHeader.addStyleName("h2");
        lblHeader.addStyleName("colored");
        formLayout.addComponent(lblHeader);

        delete = new Button("Remove Student");
        delete.addStyleName("danger");

        delete.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                getUI().getNavigator().navigateTo("Delete");
            }
        });

        refresh = new Button("Refresh");
        refresh.addStyleName("orange");

        refresh.addClickListener(new Button.ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {

                Page.getCurrent().reload();
                Notification.show("Page Refreshed!");
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

        footer.addComponent(delete);
        footer.addComponent(refresh);
        footer.addComponent(logout);

        final Table table = new Table();

        table.setStyleName("iso3166");
        table.setPageLength(6);
        table.setSizeFull();
        table.setSelectable(true);
        table.setMultiSelect(false);
        table.setImmediate(true);
        table.setColumnReorderingAllowed(true);
        table.setColumnCollapsingAllowed(true);

        table.addContainerProperty("Fullname", String.class, null);
        table.addContainerProperty("Username", String.class, null);
        table.addContainerProperty("Password", String.class, null);
        table.addContainerProperty("Address", String.class, null);
        table.addContainerProperty("DOB", String.class, null);
        table.addContainerProperty("Gender", String.class, null);
        table.addContainerProperty("Email", String.class, null);
        table.addContainerProperty("Contact", String.class, null);
        

        ResultSet resultSet = null;
        String sql = "SELECT * FROM STUDENT_REGISTRATION";

        try (Connection con = MyConnect.connect(); PreparedStatement ps = con.prepareStatement(sql);) {

            resultSet = ps.executeQuery();
            int id = 1;
            while (resultSet.next()) {

                table.addItem(new Object[] { resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getString(8) }, new Integer(id));

                id += 1;
                height += 50;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        lblHeader1 = new Label("Student Records");
        lblHeader1.addStyleName("h2");
        lblHeader1.addStyleName("colored");
        formLayout.addComponent(lblHeader1);

        table.setWidth("100%");
        table.setHeight(height + "px");
        addComponent(table);
        
        //Optional 
        Button b = new Button("Show",
        	    new Button.ClickListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub
						Object rowId = table.getValue();
						
        	            String fullname = table.getContainerProperty(rowId,"Fullname").getValue().toString();
        	            String username = table.getContainerProperty(rowId,"Username").getValue().toString();
        	            
        	            TextField fullName = new TextField("Fullname");
        	            fullName.setValue(fullname.toString());

        	            TextField userName = new TextField("UserName");
        	            userName.setValue(username);
        	            
        	            addComponent(fullName);
        	            addComponent(userName);
        	            
					}
        	});
        addComponent(b);
        
    }

    @Override
    public void enter(ViewChangeEvent event) {

    }

}
