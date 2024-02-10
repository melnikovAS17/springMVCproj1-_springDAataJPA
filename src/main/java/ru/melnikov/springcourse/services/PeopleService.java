package ru.melnikov.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.melnikov.springcourse.models.Person;
import ru.melnikov.springcourse.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
   private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> showAll(){
        return peopleRepository.findAll();
    }

    public Person showOne(int id){
        Optional<Person> personHas = peopleRepository.findById(id);
        return personHas.orElse(null);
    }
    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }
    @Transactional
    public  void  update(int id, Person personToUpdate){
        //Назначаем id чтобы spring видел, что данный человек уже ксть в бд, тогда он его обновит
        //Можно также передать id из формы, присваивая id в скрытом поле
        personToUpdate.setId(id);
        peopleRepository.save(personToUpdate);
    }
    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
    //Method for spring validator
    public Optional<Person> index(String email){
        return peopleRepository.findByEmail(email).stream().findAny();
    }
}
