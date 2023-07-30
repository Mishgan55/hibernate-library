package khorsun.hibernate.library.controllers;

import khorsun.hibernate.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
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
}
