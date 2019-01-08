package com.kramar.sample.domain;

import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "email")
public class Email {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String message;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> recipients;
    private LocalDate date;

    public Email() {
        name = Strings.EMPTY;
        message = Strings.EMPTY;
        recipients = new ArrayList<>();
        date = LocalDate.now();
    }

    public Email(String name, String message, List<String> recipients, LocalDate date) {
        this.name = name;
        this.message = message;
        this.recipients = recipients;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
