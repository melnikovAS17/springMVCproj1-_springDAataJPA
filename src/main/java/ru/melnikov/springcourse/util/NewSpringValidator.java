package ru.melnikov.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.melnikov.springcourse.models.Person;
import ru.melnikov.springcourse.services.PeopleService;

@Component
public class NewSpringValidator implements Validator {
    private final PeopleService peopleService;
    @Autowired
    public NewSpringValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(peopleService.index(person.getEmail()).isPresent())
            errors.rejectValue("email","","this email already taken");
    }
}
