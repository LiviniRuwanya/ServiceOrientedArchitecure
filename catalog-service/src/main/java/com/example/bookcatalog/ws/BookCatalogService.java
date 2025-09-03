package com.example.bookcatalog.ws;

import jakarta.jws.WebService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@WebService(
        serviceName = "BookCatalogService",
        portName = "BookCatalogPort",
        targetNamespace = "http://ws.bookcatalog.example.com/",
        endpointInterface = "com.example.bookcatalog.ws.BookCatalogPortType"
)
public class BookCatalogService implements BookCatalogPortType {

    private final Map<String, Book> idToBook = new ConcurrentHashMap<>();

    public BookCatalogService() {
        // seed some data
        addBook(new Book(UUID.randomUUID().toString(), "Clean Code", "Robert C. Martin", 39.99));
        addBook(new Book(UUID.randomUUID().toString(), "Effective Java", "Joshua Bloch", 45.50));
    }

    @Override
    public Book getBook(String id) {
        return idToBook.get(id);
    }

    @Override
    public List<Book> listBooks() {
        return new ArrayList<>(idToBook.values());
    }

    @Override
    public Book addBook(Book book) {
        if (book.getId() == null || book.getId().isBlank()) {
            book.setId(UUID.randomUUID().toString());
        }
        idToBook.put(book.getId(), book);
        return book;
    }
}


