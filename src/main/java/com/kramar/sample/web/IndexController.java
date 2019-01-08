package com.kramar.sample.web;

import com.kramar.sample.domain.Person;
import com.kramar.sample.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by mbart on 28.02.2016.
 */
//@Controller
public class IndexController {

  @Autowired
  private PersonService personService;

  @RequestMapping("/")
  public String showIndex(Model model) {
    List<Person> personList = personService.loadAll();

    model.addAttribute("personList", personList);

    return "index"; // return index.html Template
  }
}
