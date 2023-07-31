# hibernate-library
Developed a web application for the library. Librarians
should be able to register readers, issue them
books and release books (after the reader returns
book back to the library. With all CRUD operations using Hibernate,ThymeLeaf and SpringData JPA.
1) Added pagination for books.
There may be many books and they may not fit on one page in
browser. To solve this problem, the controller method can
give out not only all the books at once, but also break the issue into pages.
2) Added sorting books by year. The controller method can
give out books in sorted order.
3) Created a book search page. Enter initial letters in the field on the page
title of the book, we get the full title of the book and the name of the author. Also, if
the book is now with someone, we get the name of this person.
4) Added an automatic check that the person has overdue the return
books
