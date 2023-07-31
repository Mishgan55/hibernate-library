package khorsun.hibernate.library.repositories;

import khorsun.hibernate.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

List<Book> findBookByTitleStartingWith(String title);

}
