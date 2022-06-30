package com.bridglabz.bookstoreapps.service;

import com.bridglabz.bookstoreapps.dto.BookDetailsDTO;
import com.bridglabz.bookstoreapps.model.BookDetails;

import java.util.List;

public interface IBookDetailsService {

    List<BookDetails> showAllBooks(String token);

    BookDetails getBookById(String token, int bookId);

    BookDetails addBook(String token, BookDetailsDTO bookDto);

    void deleteBook(String token, int bookId);

    BookDetails updateBook(String token, int bookId, BookDetailsDTO bookdto);

    BookDetails updateBookPrice(String token, int bookId, int bookPrice);

    BookDetails updateBookQuantity(String token, int bookId, int quantity);
}