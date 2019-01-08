package com.kramar.sample.vaadin.customizing;

import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringListField2 extends CustomField<List<String>> {

    private VerticalLayout mainVerticalLayout;
    private List<String> value;
    private Map<Button, HorizontalLayout> textFieldButtonMap;

    public StringListField2(String caption) {
        setCaption(caption);
    }

    @Override
    protected Component initContent() {
        mainVerticalLayout = new VerticalLayout();
        value = new ArrayList<>();
        textFieldButtonMap = new HashMap<>();
        mainVerticalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        createInputItem(Strings.EMPTY);



//        value.add(Strings.EMPTY);

//        TextField textField = new TextField();
//        textField.focus();
//        fields.addComponent(textField);
//        addEmptyItem();
//        GridLayout content = new GridLayout(2,1);
//        content.addComponent(fields);

//        Button addButton = new Button("+", this::addItem);
//        content.addComponent(addButton);
//        content.setComponentAlignment(addButton, Alignment.BOTTOM_CENTER);
//        return content;
        return mainVerticalLayout;
    }

    private void createInputItem(String text) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextField textField = new TextField(text);
        Button addButton = new Button("+", this::addItem);
        horizontalLayout.addComponent(textField);
        horizontalLayout.setComponentAlignment(textField, Alignment.TOP_LEFT);
        horizontalLayout.addComponent(addButton);
        horizontalLayout.setComponentAlignment(addButton, Alignment.TOP_RIGHT);
        textFieldButtonMap.put(addButton, horizontalLayout);
        mainVerticalLayout.addComponent(horizontalLayout);
    }

    private void addItem(Button.ClickEvent event) {
        Button eventButton = event.getButton();
        HorizontalLayout horizontalLayout = textFieldButtonMap.get(eventButton);
        TextField textField = (TextField) horizontalLayout.getComponent(0);
        if (StringUtils.isNotEmpty(textField.getValue())) {
            eventButton.setCaption("-");
            eventButton.addClickListener(this::deleteItem);
            createInputItem(Strings.EMPTY);
        } else {
            Notification.show("Please, specify recipient! ", Notification.Type.WARNING_MESSAGE);

        }
//        value.add(Strings.EMPTY);


//        HorizontalLayout horizontalLayout = new HorizontalLayout();
//        TextField textField = new TextField(Strings.EMPTY);
//        Button addButton = new Button("+", this::addItem);
//        textFieldButtonMap.put(addButton, horizontalLayout);
//        horizontalLayout.addComponents(textField, addButton);
//        mainVerticalLayout.addComponent(horizontalLayout);
//

//        TextField textField = new TextField();
//        Button deleteItem = new Button("-", this::deleteItem);
//        HorizontalLayout horizontalLayout = new HorizontalLayout();
//        horizontalLayout.addComponents(textField, deleteItem);
//        textField.addValueChangeListener(valueChange -> {
//            int index = mainVerticalLayout.getComponentIndex(textField);
//            value.set(index, textField.getValue());
//        });
//        textField.focus();
//        mainVerticalLayout.addComponent(textField);
    }

    private void deleteItem(Button.ClickEvent event) {
        Button eventButton = event.getButton();
        HorizontalLayout horizontalLayout = textFieldButtonMap.get(eventButton);
        textFieldButtonMap.remove(eventButton);
        mainVerticalLayout.removeComponent(horizontalLayout);
//        value.add(Strings.EMPTY);

//        TextField textField = (TextField) event.getButton().getParent().iterator().next();
//        Button addButton = new Button("-", this::addItem);
//        HorizontalLayout horizontalLayout = new HorizontalLayout();
//        horizontalLayout.addComponents(textField, addButton);
//        textField.addValueChangeListener(valueChange -> {
//            int index = mainVerticalLayout.getComponentIndex(textField);
//            value.remove(index);
//        });
//        textField.focus();
//        mainVerticalLayout.addComponent(textField);
    }

    private void addEmptyItem() {
//        value.add(Strings.EMPTY);

        TextField textField = new TextField("");
        textField.addValueChangeListener(valueChange -> {
            value.add(textField.getValue());
        });
        textField.focus();
        mainVerticalLayout.addComponent(textField);
    }

    @Override
    public Class<? extends List<String>> getType() {
//        return ArrayList<String>.class;
        return (Class<List<String>>) ((List<String>) new ArrayList<String>()).getClass();
//        return (Class<List<String>>) Arrays.stream(ArrayList.class.getInterfaces())
//                .filter(a -> a.isAssignableFrom(List.class)).findAny().orElseThrow(RuntimeException::new);
    }

    @Override
    public List<String> getValue() {
        return textFieldButtonMap
                .keySet()
                .stream()
                .map(key -> ((TextField) textFieldButtonMap.get(key).getComponent(1)).getValue())
                .collect(Collectors.toList());
    }

    @Override
    public void setValue(List<String> newFieldValues) {
        mainVerticalLayout.removeAllComponents();
        createInputItem(Strings.EMPTY);
        newFieldValues.forEach(this::createInputItem);
    }

}
