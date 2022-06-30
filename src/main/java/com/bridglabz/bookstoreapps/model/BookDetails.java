package com.bridglabz.bookstoreapps.model;

import com.bridglabz.bookstoreapps.dto.BookDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book_storage")
public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;

    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private String bookLogo;
    private int bookPrice;
    private int bookQuantity;

    public void createBook(BookDetailsDTO bookDto) {
        this.bookName = bookDto.getBookName();
        this.bookAuthor = bookDto.getBookAuthor();
        this.bookDescription = bookDto.getBookDescription();
        this.bookLogo = bookDto.getBookLogo();
        this.bookPrice = bookDto.getBookPrice();
        this.bookQuantity = bookDto.getBookQuantity();
    }

    public void updateBookData(BookDetailsDTO bookDto) {
        this.bookName = bookDto.getBookName();
        this.bookAuthor = bookDto.getBookAuthor();
        this.bookDescription = bookDto.getBookDescription();
        this.bookLogo = bookDto.getBookLogo();
        this.bookPrice = bookDto.getBookPrice();
        this.bookQuantity = bookDto.getBookQuantity();
    }
}
