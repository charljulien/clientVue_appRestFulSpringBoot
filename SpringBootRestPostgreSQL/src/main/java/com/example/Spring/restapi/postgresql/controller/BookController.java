package com.example.Spring.restapi.postgresql.controller;


import com.example.Spring.restapi.postgresql.model.Book;
import com.example.Spring.restapi.postgresql.repo.BookRepository;
import com.example.Spring.restapi.postgresql.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BookController {


    BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/book")
    public Book createBook(@RequestBody Book book){
        return bookService.insert(book);
    }

    @GetMapping("/book") //books
    public List<Book> getAllBook(){
        System.out.println("Get all Books...");

        return bookService.getAllBooks();
    }

    @GetMapping("/book/name/{name}") //books
    public List<Book> findByName(@PathVariable String name) {

        List<Book> books = bookService.findByName(name);
        System.out.println(String.valueOf(books));
        return books;
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") long id) {
        System.out.println("Delete Book with ID = " + id + "...");

        bookService.deleteBook(id);

        return new ResponseEntity<>("Book has been deleted!", HttpStatus.OK);
    }

    @PutMapping("/book/{id}") //books
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        System.out.println("Update Book with ID = " + id + "...");

        Optional<Book> bookData = bookService.verifyBook(id);

        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setName(book.getName());
//            _book.setReleaseDate(book.getReleaseDate());
//            _book.setRead(book.isRead());
            return new ResponseEntity<>(bookService.insert(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
