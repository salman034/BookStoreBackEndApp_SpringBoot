package com.bridglabz.bookstoreapps.controller;

import com.bridglabz.bookstoreapps.dto.OrderDTO;
import com.bridglabz.bookstoreapps.dto.ResponseDTO;
import com.bridglabz.bookstoreapps.email.EmailSenderService;
import com.bridglabz.bookstoreapps.model.OrderData;
import com.bridglabz.bookstoreapps.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderservice")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private EmailSenderService emailService;

    /**
     * Ability to place a order and sending email to user regarding that
     * @param token
     * @param orderDTO
     * @param bookId
     * @return ResponseEntity
     */
    @PostMapping("/placeorder")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestHeader(name = "token") String token,
                                                  @RequestBody OrderDTO orderDTO,
                                                  @RequestParam int bookId) {
        OrderData orderData = orderService.createOrderData(token, orderDTO, bookId);
        ResponseDTO responseDTO = new ResponseDTO("Order Successfully Placed", orderData);
        emailService.sendEmail(orderData.getUserData().getEmailId(), "Order Placed Successfully.",
                "Dear " + orderData.getUserData().getFirstName()
                        + ", You have Successfully placed order of Book : "
                        + orderData.getBookDetails().getBookName());
        return  new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to cancel order by order id and sending cancellation mail to user
     * @param token
     * @param orderId
     * @return ResponseEntity
     */
    @PutMapping("/cancelorder/{orderId}")
    public ResponseEntity<ResponseDTO> cancelOrder(@RequestHeader(name = "token") String token,
                                                   @PathVariable("orderId") int orderId) {
        OrderData orderData = orderService.cancelOrder(token, orderId);
        ResponseDTO responseDTO = new ResponseDTO("Order Canceled Successfully", orderData);
        emailService.sendEmail(orderData.getUserData().getEmailId(), "Order Canceled Successfully.",
                "Dear " + orderData.getUserData().getFirstName()
                        + ", You have Successfully cancelled order of Book : "
                        + orderData.getBookDetails().getBookName());
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Abiluty to show all orders of a user
     * @param token
     * @return ResponseEntity
     */
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllOrdersByUserId(@RequestHeader(name = "token") String token) {
        List<OrderData> orders = orderService.getAllOrdersOfUser(token);
        ResponseDTO responseDTO = new ResponseDTO("All Orders Of User Retrived", orders);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

    /**
     * Ability to show all order data in order repository
     * @return ResponseEntity
     */
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        List<OrderData> orderDataList = orderService.findAllOrders();
        ResponseDTO responseDTO = new ResponseDTO("All Orders Data Retrived", orderDataList);
        return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }

}