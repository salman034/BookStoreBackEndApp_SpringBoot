package com.bridglabz.bookstoreapps.service;

import com.bridglabz.bookstoreapps.dto.OrderDTO;
import com.bridglabz.bookstoreapps.model.OrderData;

import java.util.List;

public interface IOrderService {
    OrderData createOrderData(String token, OrderDTO orderDTO, int bookId);

    OrderData cancelOrder(String token, int orderId);

    List<OrderData> getAllOrdersOfUser(String token);

    List<OrderData> findAllOrders();
}
