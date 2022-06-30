package com.bridglabz.bookstoreapps.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDate;
    private int price;
    private int quantity;
    private String address;
    private boolean cancel = false;
}