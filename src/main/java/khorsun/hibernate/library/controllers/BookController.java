package khorsun.hibernate.library.controllers;

import khorsun.hibernate.library.models.Book;
import khorsun.hibernate.library.models.Person;
import khorsun.hibernate.library.services.BookService;
import khorsun.hibernate.library.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final PersonService personService;
    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }


    @GetMapping()
    public String index(Model model, @RequestParam(value="page",required = false)Integer page,
      @RequestParam(value = "books_per_page",required = false)Integer booksPerPage,
      @RequestParam(value = "sort_by_year",required = false)boolean sortByYear){
        if (booksPerPage==null||page==null){
            model.addAttribute("books",bookService.findAllBooks(sortByYear));
        }
        else {
            model.addAttribute("books",bookService.findAllBooksWithPagination(page,booksPerPage,sortByYear));
        }

        return "book/index";
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id")int id, Model model,
                           @ModelAttribute("person")Person person){
        model.addAttribute("book",bookService.findOneBook(id));

        Optional<Person> owner = Optional.ofNullable(bookService.getBookOwner(id));

        if (owner.isPresent()){
            model.addAttribute("owner",owner.get());
        }else {
            model.addAttribute("people",personService.findAll());
        }
        return "book/show";
    }
    @GetMapping("/new")
    public String formForCreatingBook(@ModelAttribute("book")Book book){
        return "book/new";
    }
    @PostMapping()
    public String createBook(@ModelAttribute("book")Book book){
        bookService.createBook(book);
        return "redirect:/book";
    }
    @GetMapping("/{id}/edit")
    public String formForUpdatingBook(@PathVariable("id")int id,Model model){
        model.addAttribute("book",bookService.findOneBook(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id")int id ,@ModelAttribute("book")Book book){
        bookService.update(id,book);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id")int id){
        bookService.delete(id);
        return "redirect:/book";
    }
}
