package com.bridglabz.bookstoreapps.model;

import com.bridglabz.bookstoreapps.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Order_details")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDate;

    private int price;
    private int quantity;
    private String address;

    @ManyToOne
    @JoinColumn(name = "userfk", referencedColumnName = "UserId")
    private UserRegistrationData userData;

    @ManyToOne
    @JoinColumn(name = "bookfk", referencedColumnName = "BookId")
    private BookDetails bookDetails;

    private boolean cancel = false;

    public void createOrder(OrderDTO orderDTO) {
        this.address = orderDTO.getAddress();
        this.orderDate = LocalDate.now();
        this.price = orderDTO.getPrice();
        this.quantity = orderDTO.getQuantity();
        this.cancel = false;
    }
}