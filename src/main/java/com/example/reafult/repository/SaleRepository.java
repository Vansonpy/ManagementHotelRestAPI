package com.example.reafult.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reafult.entities.Sales;

@Repository(value = "saleRepository")
public interface SaleRepository extends JpaRepository<Sales, Integer> {
	List<Sales> findByStatus(Integer status);

}
