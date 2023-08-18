package ru.plyusnin.springcource.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.plyusnin.springcource.dao.PersonDAO;
import ru.plyusnin.springcource.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")  Person person){
        return "people/new";
    }

    @PostMapping
    public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personDAO.addPerson(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.show(id));
        return "/people/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "/people/edit";
        personDAO.editPerson(id, person);
        return "redirect:/people";
    }

    @PostMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personDAO.deletePerson(id);
        return "redirect:/people";
    }
}
