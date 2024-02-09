package ru.melnikov.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.melnikov.springcourse.DAO.PersonDAO;
import ru.melnikov.springcourse.models.Person;
@Component
public class NewSpringValidator implements Validator {
    private final PersonDAO personDAO;
    @Autowired
    public NewSpringValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(personDAO.index(person.getEmail()).isPresent())
            errors.rejectValue("email","","this email already taken");
    }
}
