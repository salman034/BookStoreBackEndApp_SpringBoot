package com.bridglabz.bookstoreapps.service;

import com.bridglabz.bookstoreapps.dto.CartDTO;
import com.bridglabz.bookstoreapps.exception.UserRegistrationException;
import com.bridglabz.bookstoreapps.model.BookDetails;
import com.bridglabz.bookstoreapps.model.CartData;
import com.bridglabz.bookstoreapps.model.UserRegistrationData;
import com.bridglabz.bookstoreapps.repository.BookDetailsRepository;
import com.bridglabz.bookstoreapps.repository.CartRepository;
import com.bridglabz.bookstoreapps.repository.UserRegistrationRepository;
import com.bridglabz.bookstoreapps.tokenutil.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    /**
     * Autowiring CartRepository, BookDetailsRepository, TokenUtil, UserRegistrationRepository classes
     */
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private BookDetailsRepository bookRepo;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserRegistrationRepository userRepo;


    /**
     * This method adds book to cart by book id taking token from user
     * @param token
     * @param bookId
     * @param cartDTO
     * @return CartData
     */
    @Override
    public CartData addToCart(String token, int bookId, CartDTO cartDTO) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(userId);
        if (isUserPresent.isPresent()) {
            BookDetails book = bookRepo.findByBookId(bookId).orElse(null);
            CartData cart = new CartData(isUserPresent.get(), book, cartDTO.getQuantity());
            return cartRepo.save(cart);
        } else throw new UserRegistrationException("User Id Not Found");
    }

    /**
     * This method shows all cart data taking token from user
     * @param token
     * @return list of CartData
     */
    @Override
    public List<CartData> getAllCart(String token) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(userId);
        if (isUserPresent.isPresent()) {
            return cartRepo.findAll();
        } else return null;
    }

    /**
     * This method removes cart taking cart id and token from user
     * @param token
     * @param cartId
     */
    @Override
    public void removeCart(String token, int cartId) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(userId);
        if (isUserPresent.isPresent()) {
            cartRepo.deleteById(cartId);
        } else throw new UserRegistrationException("Cart Id not Found");
    }

    /**
     * This method updates cart quantity taking token from user
     * @return CartData
     */
    @Override
    public CartData updateCartQuantity(String token, int cartId, int quantity) {
        int userId = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(userId);
        if (isUserPresent.isPresent()) {
            CartData cart = cartRepo.findById(cartId)
                    .orElseThrow(() -> new UserRegistrationException("Cart not Found"));
            cart.setQuantity(quantity);
            return cartRepo.save(cart);
        } else throw new UserRegistrationException("User not found");
    }
}