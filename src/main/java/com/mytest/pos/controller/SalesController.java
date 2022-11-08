package com.mytest.pos.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.mytest.pos.model.dto.DateSalesPoint;
import com.mytest.pos.model.dto.OutputPriceAndPoint;
import com.mytest.pos.service.SalesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SalesController {

	private final SalesService salesService;

	@MutationMapping
	public OutputPriceAndPoint recordNewSales(@Argument String price, @Argument Float price_modifier,
			@Argument String payment_method, @Argument String datetime) {
		return salesService.addSales(price, price_modifier, payment_method, datetime);
	}

	@QueryMapping
	public List<DateSalesPoint> sales(@Argument String startDateTime, @Argument String endDateTime) {
		return salesService.findByDatetimeBetween(startDateTime, endDateTime);
	}
}
