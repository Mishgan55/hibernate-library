package khorsun.hibernate.library.services;

import khorsun.hibernate.library.models.Book;
import khorsun.hibernate.library.models.Person;
import khorsun.hibernate.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooksWithPagination(int page,int booksPerPage,boolean sort){
        if (sort){
        return bookRepository.findAll(PageRequest.of(page,booksPerPage,Sort.by("year"))).getContent();}
        else{
            return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();}
    }
    public List<Book> findAllBooks(boolean sort){
        if (sort){
        return bookRepository.findAll(Sort.by("year"));}
        else{
            return bookRepository.findAll();}
    }
    public Book findOneBook(int id){
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }
    public Person getBookOwner(int id){
        return bookRepository.findById(id).map(Book::getPerson).orElse(null);
    }
    @Transactional
    public void createBook(Book book){
        bookRepository.save(book);
    }
    @Transactional
    public void update(int id,Book book){
        book.setId(id);
        bookRepository.save(book);
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
    @Transactional
    public void release(int id){
         bookRepository.findById(id).ifPresent(book -> {
             book.setPerson(null);
         });



    }

}
