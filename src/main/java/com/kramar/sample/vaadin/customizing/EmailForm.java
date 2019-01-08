package com.kramar.sample.vaadin.customizing;

import com.kramar.sample.domain.Email;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static com.kramar.sample.vaadin.converter.LocalDateToDateConverter.LOCAL_DATE_TO_DATE_CONVERTER;
import static com.kramar.sample.vaadin.converter.PersistentBagToArrayListConverter.PERSISTENT_BAG_TO_ARRAY_LIST_CONVERTER;

public class EmailForm extends Panel {

    @PropertyId("name")
    private TextField nameTextField = new TextField("Name");
    @PropertyId("message")
    private TextField message = new TextField("Message");
    @PropertyId("recipients")
    private StringListField recipients = new StringListField("Recipients");
    @PropertyId("date")
    private DateField date = new DateField("Date");

    private Runnable onSaveOrDiscard;
    private BeanFieldGroup<Email> emailFieldGroup;

    public EmailForm() {
    }

    public EmailForm(Email email, Runnable onSaveOrDiscard) {
        this.onSaveOrDiscard = onSaveOrDiscard;
        init(email);
    }

    private void init(Email email) {
        setCaption("Email form");
        initDatePiker();
        initRecipient();
//        name.setValue(Strings.EMPTY);
//        message.setValue(Strings.EMPTY);
//        recipients.setValue(new ArrayList<>());

        emailFieldGroup = new BeanFieldGroup<>(Email.class);
        emailFieldGroup.setItemDataSource(email);
        emailFieldGroup.buildAndBindMemberFields(this);

        VerticalLayout content = new VerticalLayout();
        content.setMargin(true);
        content.setSizeUndefined();
        setContent(content);
        setSizeUndefined();

        FormLayout form = new FormLayout();
        form.addComponents(date, nameTextField, message, recipients);
        form.setSizeFull();
//        VerticalLayout verticalLayout = new VerticalLayout();
//        verticalLayout.addComponent(form);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(new Button("Save", this::save), new Button("Cancel", this::cancel));
        content.addComponents(form, horizontalLayout);
    }

    private void initDatePiker() {
        date.setDateFormat("dd-MM-yyyy");
        date.setConverter(LOCAL_DATE_TO_DATE_CONVERTER);
        date.addValueChangeListener((Property.ValueChangeEvent event) -> {
            date.setValue((Date) event.getProperty().getValue());
        });
    }

    private void initRecipient() {
        recipients.setConverter(PERSISTENT_BAG_TO_ARRAY_LIST_CONVERTER);
    }

    private void save(Button.ClickEvent event) {

        Email email = emailFieldGroup.getItemDataSource().getBean();
        email.setName(nameTextField.getValue());
        email.setMessage(message.getValue());
        email.setRecipients(recipients.getValue());
        email.setDate(ZonedDateTime.ofInstant(date.getValue().toInstant(), ZoneId.systemDefault()).toLocalDate());

        try {
            emailFieldGroup.commit();
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
        onSaveOrDiscard.run();
    }

    private void cancel(Button.ClickEvent event) {
        emailFieldGroup.discard();
        onSaveOrDiscard.run();
    }

    public TextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(TextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public TextField getMessage() {
        return message;
    }

    public void setMessage(TextField message) {
        this.message = message;
    }

    public StringListField getRecipients() {
        return recipients;
    }

    public void setRecipients(StringListField recipients) {
        this.recipients = recipients;
    }

    public DateField getDate() {
        return date;
    }

    public void setDate(DateField date) {
        this.date = date;
    }
}
