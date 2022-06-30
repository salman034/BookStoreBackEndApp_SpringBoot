package com.bridglabz.bookstoreapps.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addcart")
public class CartData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "userfkeyid", referencedColumnName = "userId")
    private UserRegistrationData user;

    @ManyToOne
    @JoinColumn(name = "bookfkeyid", referencedColumnName = "bookId")
    private BookDetails book;
    private int quantity;

    public CartData(UserRegistrationData user, BookDetails book, int quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }

}