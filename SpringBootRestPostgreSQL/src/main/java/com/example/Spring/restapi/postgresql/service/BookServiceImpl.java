package com.example.Spring.restapi.postgresql.service;

import com.example.Spring.restapi.postgresql.model.Book;
import com.example.Spring.restapi.postgresql.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {


    @Autowired
    private BookRepository bookRepository;

    /**
     * @return List of Books
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * @param book
     * @return Book entity
     */
    public Book insert(Book book) {
        return !bookRepository.existsById(book.getId()) ? bookRepository.save(book) : null;
    }

    /**
     * Delete Book
     *
     * @param bookId
     * @return status of a HTTP response
     */
    public ResponseEntity<Book> deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Find Book by id
     *
     * @param id
     * @return Book
     */
    public Optional<Book> verifyBook(long id) {
        return bookRepository.findById(id);
    }

    /**
     * Find book by author
     *
     * @param name
     * @return Book
     */
    public List<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }
//
//    /**
//     * Update Book
//     *
//     * @param id
//     * @param book
//     * @return Updated Book and status of a HTTP response
//     */
//    public ResponseEntity<Book> updateBookPrice(int id, Book book) {
//        if (verifyBook(id).isPresent()) {
//            verifyBook(id).get();
//            book.setPrice(book.getPrice());
//            return new ResponseEntity<>(bookRepository.save(book), HttpStatus.OK);
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
