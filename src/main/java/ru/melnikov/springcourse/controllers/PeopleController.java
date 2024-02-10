package ru.melnikov.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.melnikov.springcourse.models.Person;
import ru.melnikov.springcourse.services.PeopleService;
import ru.melnikov.springcourse.util.NewSpringValidator;

import javax.validation.Valid;

@Controller
public class PeopleController {
    private final NewSpringValidator newSpringValidator;
    private final PeopleService peopleService;
    @Autowired
    public PeopleController(NewSpringValidator newSpringValidator, PeopleService peopleService) {
        this.newSpringValidator = newSpringValidator;
        this.peopleService = peopleService;
    }

    @GetMapping("/show")
    public String showAll(Model model){
        model.addAttribute("people",peopleService.showAll());
        return "first/showAll";
    }

    @GetMapping("/show/{id}")
    public String showIndex(@PathVariable("id") int id, Model model){
        model.addAttribute("person",peopleService.showOne(id));
        return "first/index";
    }

    @GetMapping("/show/new")
    public String formForAddPerson(Model model){
        model.addAttribute("person", new Person());
        return "forms/newPersonForm";
    }
    @PostMapping("/show")
    public String addPerson(@ModelAttribute("person") @Valid Person person,
                            BindingResult bindingResult){
        newSpringValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()) return "forms/newPersonForm";
    peopleService.save(person);
    return "redirect:/show";
    }

    @GetMapping("/show/{id}/edit")
    public String formForEditPerson(@PathVariable("id") int id,
                                    Model model){
        model.addAttribute("person", peopleService.showOne(id));
        return "forms/editPersonForm";
    }

    @PatchMapping("/show/{id}")
    public String editPerson(@PathVariable("id") int id,
                             @ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult){
        newSpringValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()) return "forms/editPersonForm";
        peopleService.update(id,person);
        return "redirect:/show";
    }

    @DeleteMapping("/show/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/show";
    }
}
