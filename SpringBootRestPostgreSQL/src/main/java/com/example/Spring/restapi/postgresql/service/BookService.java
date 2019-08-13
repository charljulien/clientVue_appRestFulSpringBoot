package com.example.Spring.restapi.postgresql.service;

import com.example.Spring.restapi.postgresql.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    Iterable<Book> getAllBooks();

    Book insert(Book book);

    ResponseEntity<Book> deleteBook(long bookId);

    Optional<Book> verifyBook(long id);

    List<Book> findByName(String name);
}
