package com.kramar.sample.service;

import com.kramar.sample.domain.Email;

import java.util.List;

/**
 * Created by mbart on 28.02.2016.
 */
public interface EmailService {

  List<Email> loadAll();

  void deleteAll();

  void deleteById(Long id);

  void delete(Email email);

  void deleteInBatch(List<Email> persons);

  void save(Email email);
}
