package com.kramar.sample.vaadin.customizing;

import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.*;
import java.util.stream.Collectors;

public class StringListField2 extends CustomField<List<String>> {

    private VerticalLayout mainVerticalLayout;
    private Map<Button, HorizontalLayout> textFieldButtonMap;

    public StringListField2(String caption) {
        setCaption(caption);
    }

    @Override
    protected Component initContent() {
//        fireValueChange(true);
        mainVerticalLayout = new VerticalLayout();
        textFieldButtonMap = new HashMap<>();
        mainVerticalLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        createInputItem(Strings.EMPTY);
        return mainVerticalLayout;
    }

    private void createInputItem(String text) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextField textField = new TextField();
        textField.setValue(text);
        Button addButton = new Button("+", this::addItem);
        horizontalLayout.addComponents(textField, addButton);
        horizontalLayout.setComponentAlignment(textField, Alignment.TOP_LEFT);
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
    }

    // TODO: 1/8/2019 not work correctly!
    private void deleteItem(Button.ClickEvent event) {
        Button eventButton = event.getButton();
        HorizontalLayout horizontalLayout = textFieldButtonMap.get(eventButton);
        textFieldButtonMap.remove(eventButton);
        mainVerticalLayout.removeComponent(horizontalLayout);

//        mainVerticalLayout.removeAllComponents();
//        for (Component component : textFieldButtonMap.values()) {
//            mainVerticalLayout.addComponent(component);
//        }
    }



    @Override
    public Class<? extends List<String>> getType() {
        return (Class<List<String>>) ((List<String>) new ArrayList<String>()).getClass();
//        return (Class<List<String>>) Arrays.stream(ArrayList.class.getInterfaces())
//                .filter(a -> a.isAssignableFrom(List.class)).findAny().orElseThrow(RuntimeException::new);
    }

    @Override
    public List<String> getValue() {
        fireValueChange(true);
        return textFieldButtonMap
                .keySet()
                .stream()
                .map(key -> ((TextField) textFieldButtonMap.get(key).getComponent(0)).getValue())
                .collect(Collectors.toList());
    }

    @Override
    public void setValue(List<String> newFieldValues) {
        fireValueChange(true);
        mainVerticalLayout.removeAllComponents();
        textFieldButtonMap.clear();
        createInputItem(Strings.EMPTY);
        newFieldValues.forEach(this::createInputItem);
    }

}
