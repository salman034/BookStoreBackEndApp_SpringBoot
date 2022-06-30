package com.bridglabz.bookstoreapps.service;

import com.bridglabz.bookstoreapps.dto.BookDetailsDTO;
import com.bridglabz.bookstoreapps.exception.UserRegistrationException;
import com.bridglabz.bookstoreapps.model.BookDetails;
import com.bridglabz.bookstoreapps.model.UserRegistrationData;
import com.bridglabz.bookstoreapps.repository.BookDetailsRepository;
import com.bridglabz.bookstoreapps.repository.UserRegistrationRepository;
import com.bridglabz.bookstoreapps.tokenutil.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookDetailsService implements IBookDetailsService{

    /**
     * Autowiring BookDetailsRepository, TokenUtil, UserRegistrationService,  UserRegistrationRepository classes.
     */
    @Autowired
    private BookDetailsRepository bookRepo;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserRegistrationService userService;
    @Autowired
    private UserRegistrationRepository userRepo;


    /**
     * This method shows all the books in BookRepository taking token from user
     * @param token
     * @return list of bookdetails
     */
    @Override
    public List<BookDetails> showAllBooks(String token) {
        int id = tokenUtil.decodeToken(token);

        Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
        if(isUserPresent.isPresent()) {
            return bookRepo.findAll();
        } else return null;

    }

    /**
     * This method shows book by the book id from Book Repository taking token from user
     * @param token
     * @param bookId
     * @return BookDetails
     */
    @Override
    public BookDetails getBookById(String token, int bookId) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
        if (isUserPresent.isPresent()) {
            return bookRepo.findById(bookId).orElseThrow(() -> new UserRegistrationException("Book Not Found"));
        }
        return null;
    }

    /**
     * This method adds book to book repository taking token from user
     * @param token
     * @param bookDto
     * @return BookDetails
     */
    @Override
    public BookDetails addBook(String token, BookDetailsDTO bookDto) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
        if (isUserPresent.isPresent()) {
            BookDetails book = new BookDetails();
            book.createBook(bookDto);
            bookRepo.save(book);
            return book;
        } else throw new UserRegistrationException("User Not Found");
    }

    /**
     * This method deletes book from book repository taking token and book id from user
     * @param token
     * @param bookId
     */
    @Override
    public void deleteBook(String token, int bookId) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
        if (isUserPresent.isPresent()) {
            BookDetails book = this.getBookById(token, bookId);
            bookRepo.delete(book);
        }
    }

    /**
     * This method updates book in book repository taking token from user
     * @param token
     * @param bookId
     * @param bookdto
     * @return BookDetails
     */
    @Override
    public BookDetails updateBook(String token, int bookId, BookDetailsDTO bookdto) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
        if (isUserPresent.isPresent()) {
            BookDetails book = this.getBookById(token,bookId);
            book.updateBookData(bookdto);
            return bookRepo.save(book);
        } else throw new UserRegistrationException("User Not Present");
    }

    /**
     * This method updates book price in book repository taking token and book id from user
     * @param token
     * @param bookId
     * @param bookPrice
     * @return BookDetails
     */
    @Override
    public BookDetails updateBookPrice(String token, int bookId, int bookPrice) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
        if (isUserPresent.isPresent()) {
            BookDetails book = this.getBookById(token, bookId);
            book.setBookPrice(bookPrice);
            bookRepo.save(book);
            return book;
        } else return null;
    }

    /**
     * This method updates book quantity in book repository taking token and book id from user
     * @return
     */
    @Override
    public BookDetails updateBookQuantity(String token, int bookId, int quantity) {
        int id = tokenUtil.decodeToken(token);
        Optional<UserRegistrationData> isUserPresent = userRepo.findById(id);
        if (isUserPresent.isPresent()) {
            BookDetails book = this.getBookById(token, bookId);
            book.setBookQuantity(quantity);
            bookRepo.save(book);
            return book;
        } else return null;
    }
}