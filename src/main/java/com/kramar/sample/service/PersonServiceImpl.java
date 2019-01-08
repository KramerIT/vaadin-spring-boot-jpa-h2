package com.kramar.sample.service;

import com.kramar.sample.domain.Person;
import com.kramar.sample.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mbart on 28.02.2016.
 */
@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private PersonRepository personRepository;

  @Override
  public List<Person> loadAll() {
    return personRepository.findAll();
  }

  @Override
  public void deleteAll() {
    personRepository.deleteAll();
  }

  @Override
  public void deleteById(Long id) {
    personRepository.deleteById(id);
  }

  @Override
  public void deleteInBatch(List<Person> persons) {
    personRepository.deleteInBatch(persons);
  }

}
