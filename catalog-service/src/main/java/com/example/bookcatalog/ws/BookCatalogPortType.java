package com.example.bookcatalog.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://ws.bookcatalog.example.com/", name = "BookCatalogPortType")
public interface BookCatalogPortType {

    @WebMethod(operationName = "GetBook")
    @WebResult(name = "Book")
    Book getBook(@WebParam(name = "id") String id);

    @WebMethod(operationName = "ListBooks")
    @WebResult(name = "Books")
    List<Book> listBooks();

    @WebMethod(operationName = "AddBook")
    @WebResult(name = "Book")
    Book addBook(@WebParam(name = "book") Book book);
}


