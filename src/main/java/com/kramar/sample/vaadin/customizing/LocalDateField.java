package com.kramar.sample.vaadin.customizing;

import com.vaadin.ui.*;

import java.time.LocalDate;

public class LocalDateField extends CustomField<LocalDate> {

    private VerticalLayout fields;
    private LocalDate localDate;

    public LocalDateField(String caption) {
        setCaption(caption);
        localDate = LocalDate.now();
    }

    @Override
    protected Component initContent() {
        fields = new VerticalLayout();
        fields.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        TextField textField = new TextField();
        textField.focus();
        fields.addComponent(textField);
        GridLayout content = new GridLayout(2,1);
        content.addComponent(fields);

        Button addButton = new Button("+", this::addItem);
        content.addComponent(addButton);
        content.setComponentAlignment(addButton, Alignment.BOTTOM_CENTER);
        return content;
    }

    private void addItem(Button.ClickEvent event) {
        TextField textField = new TextField();
        textField.addValueChangeListener(valueChange -> {
            int index = fields.getComponentIndex(textField);
//            getValue().set(index, textField.getValue());
        });
        textField.focus();
        fields.addComponent(textField);
    }

    @Override
    public Class<LocalDate> getType() {
        return (Class<LocalDate>) localDate.getClass();
    }

    @Override
    public LocalDate getValue() {
        return localDate;
    }

    @Override
    public void setValue(LocalDate newLocalDate) {
        localDate = newLocalDate;
    }

}
