package com.kramar.sample.vaadin.ui.oldUI;

import com.kramar.sample.domain.Person;
import com.kramar.sample.service.PersonService;
import com.kramar.sample.vaadin.customizing.StringListField_Old;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

//@Theme("mytheme")
//@SpringUI(path = "/person")
public class PersonUI extends UI{

    private VerticalLayout mainVerticalLayout;
    private Collection<Object> selectedRowsInPersonTableGrid;

    @Autowired
    PersonService personService;

    @Override
    protected void init(VaadinRequest request) {
        setupMainLayout();
        addPersonTableGrid();
        addStringListField();
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
        Label header = new Label("I LOVE VAADIN!!!");
        header.addStyleName(ValoTheme.LABEL_H1);
        horizontalLayout.addComponent(header);
        mainVerticalLayout.addComponent(horizontalLayout);
    }

    private void addPersonTableGrid() {
        Collection<Person> people = personService.loadAll();
        BeanItemContainer<Person> container = new BeanItemContainer<>(Person.class, people);
        Grid tableGrid = new Grid(container);
        tableGrid.setColumnOrder("id", "firstName", "lastName", "age");
        tableGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        tableGrid.addSelectionListener(selectionEvent -> {
            selectedRowsInPersonTableGrid = (tableGrid.getSelectionModel()).getSelectedRows();
            if (!CollectionUtils.isEmpty(selectedRowsInPersonTableGrid))
                Notification.show(
                        "Selected " + selectedRowsInPersonTableGrid.size()
                                + (selectedRowsInPersonTableGrid.size() > 1 ?" rows" : " row"),
                        Notification.Type.TRAY_NOTIFICATION);
            else
                Notification.show("Nothing selected", Notification.Type.TRAY_NOTIFICATION);
        });

        Button removeButton = new Button("Delete");
        removeButton.addClickListener((Button.ClickEvent event) -> {
            selectedRowsInPersonTableGrid.forEach(row -> tableGrid.getContainerDataSource().removeItem(row));
            personService.deleteInBatch((List<Person>)(Object)selectedRowsInPersonTableGrid);
            Notification.show(
                    "Selected " + (selectedRowsInPersonTableGrid.size() > 1 ?" rows" : " row") + " was removed",
                    Notification.Type.TRAY_NOTIFICATION);

        });
        mainVerticalLayout.addComponent(tableGrid);
        mainVerticalLayout.addComponent(removeButton);
    }

    public void addStringListField() {
        StringListField_Old stringListField = new StringListField_Old("My custom field");
        mainVerticalLayout.addComponent(stringListField);
    }


}
