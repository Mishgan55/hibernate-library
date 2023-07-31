package khorsun.hibernate.library.util;

import khorsun.hibernate.library.models.Person;
import khorsun.hibernate.library.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator {
    private final PersonService personService;
    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Person person=(Person) o;

        if (personService.findByName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName","","This name is already exist");
        }



    }
}
