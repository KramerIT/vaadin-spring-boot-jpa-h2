package com.kramar.sample.service;

import com.kramar.sample.domain.Email;
import com.kramar.sample.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mbart on 28.02.2016.
 */
@Service
public class EmailServiceImpl implements EmailService {

  @Autowired
  private EmailRepository emailRepository;

  @Override
  public List<Email> loadAll() {
    return emailRepository.findAll();
  }

  @Override
  public void deleteAll() {
    emailRepository.deleteAll();
  }

  @Override
  public void deleteById(Long id) {
    emailRepository.deleteById(id);
  }

    @Override
  public void delete(Email email) {
    emailRepository.deleteById(email.getId());
  }

  @Override
  public void deleteInBatch(List<Email> emails) {
    emailRepository.deleteInBatch(emails);
  }

  @Override
  public void save(Email email) {
    emailRepository.save(email);
  }

}
