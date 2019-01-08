package com.kramar.sample.domain;

import javax.persistence.*;

/**
 * Created by mbart on 28.02.2016.
 */
@Entity
@Table(name = "person")
public class Person {

  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
  private String age;

  public Person() {
  }

  public Person(String firstName, String lastName, String age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }
}
