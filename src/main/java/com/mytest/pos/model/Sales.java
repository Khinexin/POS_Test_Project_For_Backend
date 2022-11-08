package com.mytest.pos.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author KhineZinMyint | 6/Nov/2022
 */

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// input sales 
	@Column(precision=20, scale=2)
	private BigDecimal sales;
	@Column(precision=20, scale=2)
	private BigDecimal priceModifier;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date datetime;

	PaymentMethod paymentMethod;
	
	// calculated output 
	private String finalPrice;
	private Integer finalPoint;

}
