package khorsun.hibernate.library.controllers;

import khorsun.hibernate.library.models.Person;
import khorsun.hibernate.library.services.PersonService;
import khorsun.hibernate.library.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,Model model){
        model.addAttribute("person",personService.findOne(id));
        model.addAttribute("books",personService.showBooksByPersonId(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String formForCreatingPerson(@ModelAttribute("person")Person person){
        return "people/new";
    }
    @PostMapping()
    public String createPerson(@ModelAttribute("person")@Valid Person person, BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personService.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String formForUpdatingPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person",personService.findOne(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String updatePerson(@PathVariable("id")int id,@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        personValidator.validate(person,bindingResult);
        if (bindingResult.hasErrors()){
            return "people/new";
        }
        personService.edit(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id){
        personService.delete(id);
        return "redirect:/people";
    }
}
