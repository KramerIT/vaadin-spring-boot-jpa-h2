package com.kramar.sample.vaadin.ui;

import com.kramar.sample.domain.Email;
import com.kramar.sample.service.EmailService;
import com.kramar.sample.vaadin.customizing.EmailForm;
import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Theme("mytheme")
@SpringUI(path = "/email")
public class EmailUI extends UI{

    private VerticalLayout mainVerticalLayout;
    private Collection<Object> selectedRowsInEmailTableGrid;

    @Autowired
    EmailService emailService;

    @Override
    protected void init(VaadinRequest request) {
        setupMainLayout();
        addPersonTableGrid();
//        addStringListField();
//        addEmailForm();
    }

    private void setupMainLayout() {
        mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(mainVerticalLayout);
        addHeader();
    }

    private void addHeader() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        Label header = new Label("EMAIL UI");
        header.addStyleName(ValoTheme.LABEL_H1);
        horizontalLayout.addComponent(header);
        mainVerticalLayout.addComponent(horizontalLayout);
    }

    private void addPersonTableGrid() {
        List<Email> emails = emailService.loadAll();

        BeanItemContainer<Email> container = new BeanItemContainer<>(Email.class, emails);
        Grid tableGrid = new Grid(container);
        tableGrid.setColumnOrder("date", "name", "message", "recipients");
        tableGrid.removeColumn("id");
        tableGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        tableGrid.addSelectionListener(selectionEvent -> {
            selectedRowsInEmailTableGrid = (tableGrid.getSelectionModel()).getSelectedRows();
            if (!CollectionUtils.isEmpty(selectedRowsInEmailTableGrid))
                Notification.show(
                        "Selected " + selectedRowsInEmailTableGrid.size()
                                + (selectedRowsInEmailTableGrid.size() > 1 ?" rows" : " row"),
                        Notification.Type.TRAY_NOTIFICATION);
            else
                Notification.show("Nothing selected", Notification.Type.TRAY_NOTIFICATION);
        });

        Button addButton = new Button("Add");
        addButton.addClickListener((Button.ClickEvent event) -> {
            Window window = new Window();
            Email email = new Email();
            EmailForm emailForm = new EmailForm(email, () -> {
                emailService.save(email);
                tableGrid.getContainerDataSource().addItem(email);
                tableGrid.refreshAllRows();
                window.close();
            });
            window.setContent(emailForm);
            this.addWindow(window);
        });

        Button editButton = new Button("Edit");
        editButton.addClickListener((Button.ClickEvent event) -> {
            Window window = new Window();
            Email email = (Email) selectedRowsInEmailTableGrid.iterator().next();
            EmailForm emailForm = new EmailForm(email, () -> {
                emailService.save(email);
                tableGrid.getContainerDataSource().addItem(email);
                tableGrid.refreshAllRows();
                window.close();
            });
            window.setContent(emailForm);
            this.addWindow(window);
        });

        Button deleteButton = new Button("Delete");
        deleteButton.addClickListener((Button.ClickEvent event) -> {
            selectedRowsInEmailTableGrid.forEach(row -> {
                tableGrid.getContainerDataSource().removeItem(row);
                emailService.delete(((Email)row));
            });
            Notification.show(
                    "Selected " + (selectedRowsInEmailTableGrid.size() > 1 ?" rows" : " row") + " was removed",
                    Notification.Type.TRAY_NOTIFICATION);

        });

        mainVerticalLayout.addComponent(tableGrid);
        HorizontalLayout horizontalLayoutForButtons = new HorizontalLayout();
        horizontalLayoutForButtons.addComponents(addButton, editButton, deleteButton);
        mainVerticalLayout.addComponent(horizontalLayoutForButtons);
    }

//    public void addStringListField() {
//        StringListField stringListField = new StringListField("My custom field");
//        mainVerticalLayout.addComponent(stringListField);
//    }

    public void addEmailForm() {
        Email email = new Email("Name", "Text", Collections.EMPTY_LIST, LocalDate.now());
        EmailForm emailForm = new EmailForm(email, () -> {
            Notification.show("email form", Notification.Type.HUMANIZED_MESSAGE);
        });
        mainVerticalLayout.addComponent(emailForm);
    }

}
