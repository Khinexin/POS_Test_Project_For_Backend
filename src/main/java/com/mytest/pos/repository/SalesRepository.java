package com.mytest.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytest.pos.model.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {
	
	public List<Sales> findByDatetimeBetween(Date startDate,Date endDate);
}
