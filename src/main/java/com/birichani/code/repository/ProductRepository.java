package com.birichani.code.repository;

import com.birichani.code.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product>findByItemsCreatedBetweenLastMonth(Date monthStart, Date endMonth);
   List<Product> findByItemsReceivedToday(Date dayStart,Date dayEnd);

   List<Product> findByItemsReceivedLastWeek(Date weekStart,Date weekEnd);



}
