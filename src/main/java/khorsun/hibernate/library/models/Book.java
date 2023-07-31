package khorsun.hibernate.library.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min=2, max=50, message = "Title should between 2-50 characters")
    private String title;
    @Column(name = "author")
    @NotEmpty(message = "Author should not be empty")
    @Size(min=2,max=100, message = "Author should between 2-100 characters")
    private String author;
    @Column(name = "year")
    @Min(value = 1500, message = "Year should be greater then 1500")
    private int year;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;
    @Column(name="taken_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenTime;
    @Transient
    private boolean expired;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(Date takenTime) {
        this.takenTime = takenTime;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
