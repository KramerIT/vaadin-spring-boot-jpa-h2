package com.kramar.sample.service;

import com.kramar.sample.domain.Person;

import java.util.List;

/**
 * Created by mbart on 28.02.2016.
 */
public interface PersonService {

  List<Person> loadAll();

  void deleteAll();

  void deleteById(Long id);

  void deleteInBatch(List<Person> persons);
}
