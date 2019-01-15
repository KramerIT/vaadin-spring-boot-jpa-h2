package com.kramar.sample.vaadin.view;


import com.kramar.sample.domain.Email;
import com.kramar.sample.service.EmailService;
import com.kramar.sample.vaadin.customizing.EmailForm;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@SpringView(name = EmailView.NAME)
public class EmailView extends CustomComponent implements View {

    public final static String NAME = "email";

    private VerticalLayout mainVerticalLayout;
    private Collection<Object> selectedRowsInEmailTableGrid;

    @Autowired
    EmailService emailService;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupMainLayout();
        addPersonTableGrid();
    }

    private void setupMainLayout() {
        mainVerticalLayout = new VerticalLayout();
        mainVerticalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        this.setCompositionRoot(mainVerticalLayout);
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
            UI.getCurrent().addWindow(window);
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
            UI.getCurrent().addWindow(window);
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

}
