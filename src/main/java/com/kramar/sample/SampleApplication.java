package com.kramar.sample;

import com.kramar.sample.domain.Email;
import com.kramar.sample.domain.Person;
import com.kramar.sample.repositories.EmailRepository;
import com.kramar.sample.repositories.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories
public class SampleApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SampleApplication.class, args);

    PersonRepository personRepository = context.getBean(PersonRepository.class);
    EmailRepository emailRepository = context.getBean(EmailRepository.class);
    personRepository.save(new Person("Yri", "Kramar", "1985"));
    personRepository.save(new Person("Vova", "Litvinov", "1999"));
    personRepository.save(new Person("Shasha", "Dudin", "1984"));
    personRepository.save(new Person("Pasha", "Prokopenko", "1999"));
    personRepository.save(new Person("Demian", "Belsky", "1999"));
    personRepository.save(new Person("Maksim", "Byshnev", "1999"));
    emailRepository.save(new Email("Name", "Text", Arrays.asList("Recipient1", "Recipient2"), LocalDate.now()));
    emailRepository.save(new Email("Name1", "Text1", Arrays.asList("Recipient1", "Recipient2"), LocalDate.now()));
  }

}
