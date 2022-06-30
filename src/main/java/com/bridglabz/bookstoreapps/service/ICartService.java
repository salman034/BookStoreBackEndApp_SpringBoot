package com.bridglabz.bookstoreapps.service;

import com.bridglabz.bookstoreapps.dto.CartDTO;
import com.bridglabz.bookstoreapps.dto.ResponseDTO;
import com.bridglabz.bookstoreapps.model.CartData;

import java.util.List;

public interface ICartService {
    CartData addToCart(String token, int bookId, CartDTO cartDTO);

    List<CartData> getAllCart(String token);

    void removeCart(String token, int cartId);

    CartData updateCartQuantity(String token, int cartId, int quantity);
}
