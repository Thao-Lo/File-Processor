package com.zavis.fileprocessor.file_processor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zavis.fileprocessor.file_processor.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
