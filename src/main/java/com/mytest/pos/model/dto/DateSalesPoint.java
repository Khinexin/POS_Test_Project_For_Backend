package com.mytest.pos.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateSalesPoint {

	private String datetime;
	private String sales;
	private Integer point;
}
