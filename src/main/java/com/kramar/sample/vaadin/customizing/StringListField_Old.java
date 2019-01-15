package com.kramar.sample.vaadin.customizing;

import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class StringListField_Old extends CustomField<List<String>> {

    private VerticalLayout fields;
    private List<String> value;

    public StringListField_Old(String caption) {
        setCaption(caption);
    }

    @Override
    protected Component initContent() {
        fields = new VerticalLayout();
        value = new ArrayList<>();
        fields.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        TextField textField = new TextField();
        Button addButton = new Button("+", this::addItem);
        horizontalLayout.addComponents(textField, addButton);
        fields.addComponent(horizontalLayout);



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
        return fields;
    }

    private void addItem(Button.ClickEvent event) {
//        value.add(Strings.EMPTY);

        TextField textField = new TextField();
        Button deleteItem = new Button("-", this::deleteItem);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(textField, deleteItem);
        textField.addValueChangeListener(valueChange -> {
            int index = fields.getComponentIndex(textField);
            value.set(index, textField.getValue());
        });
        textField.focus();
        fields.addComponent(textField);
    }

    private void deleteItem(Button.ClickEvent event) {
//        value.add(Strings.EMPTY);

        TextField textField = (TextField) event.getButton().getParent().iterator().next();
        Button addButton = new Button("-", this::addItem);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(textField, addButton);
        textField.addValueChangeListener(valueChange -> {
            int index = fields.getComponentIndex(textField);
            value.remove(index);
        });
        textField.focus();
        fields.addComponent(textField);
    }

    private void addEmptyItem() {
//        value.add(Strings.EMPTY);

        TextField textField = new TextField("");
        textField.addValueChangeListener(valueChange -> {
            value.add(textField.getValue());
        });
        textField.focus();
        fields.addComponent(textField);
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
        return value;
    }

    @Override
    public void setValue(List<String> newFieldValues) {
        value = newFieldValues;
    }

}
