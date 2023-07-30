package khorsun.hibernate.library.services;

import khorsun.hibernate.library.models.Book;
import khorsun.hibernate.library.models.Person;
import khorsun.hibernate.library.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }
    public Person findOne(int id){
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }
    public List<Book> showBooksByPersonId(int id){
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()){
            Hibernate.initialize(person.get().getBook());
        return person.get().getBook();
        }
        else {
            return Collections.emptyList();
        }
    }
    @Transactional
    public void create(Person person){
        personRepository.save(person);
    }
    @Transactional
    public void  edit(int id, Person person){
        person.setId(id);
        personRepository.save(person);
    }
    @Transactional
    public void delete(int id){
        personRepository.deleteById(id);
    }

}
