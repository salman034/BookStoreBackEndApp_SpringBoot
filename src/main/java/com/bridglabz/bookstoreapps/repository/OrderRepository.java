package com.bridglabz.bookstoreapps.repository;

import com.bridglabz.bookstoreapps.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderData, Integer> {
    @Query(value = "SELECT * FROM order_details WHERE userfk=:id", nativeQuery = true)
    List<OrderData> findUserOrder(int id);

    @Query(value = "SELECT * FROM order_details WHERE cancel=:cancel", nativeQuery = true)
    List<OrderData> findOrder(boolean cancel);
}
