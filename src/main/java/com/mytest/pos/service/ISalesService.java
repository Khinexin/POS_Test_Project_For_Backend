package com.mytest.pos.service;

import java.util.List;

import com.mytest.pos.exception.DataNotFoundException;
import com.mytest.pos.model.Sales;
import com.mytest.pos.model.dto.DateSalesPoint;
import com.mytest.pos.model.dto.OutputPriceAndPoint;


public interface ISalesService{
	
	Sales findById(int id) throws DataNotFoundException;
	List<Sales> findAll();
	List<DateSalesPoint> findByDatetimeBetween(String fromDate, String toDate);
	OutputPriceAndPoint addSales(String price, Float price_modifier, String payment_method, String datetime);
}
