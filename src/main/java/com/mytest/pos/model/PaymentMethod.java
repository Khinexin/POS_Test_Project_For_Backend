package com.mytest.pos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentMethod {

	CASH("CASH", 0.09, 1, 0.05),
	CASH_ON_DELIVERY("CASH_ON_DELIVERY", 1, 1.02, 0.05),
	VISA("VISA", 0.95, 1, 0.03),
	MASTERCARD("MASTERCARD", 0.95, 1, 0.03),
	AMEX("AMEX", 0.98, 1.01, 0.02),
	JCB("JCB", 0.95, 1, 0.05);

	private String paymentMethod;
	private double modifiePriceFrom, modifyPriceTo, modifyPoint;

	public static PaymentMethod getEnumByPaymentMethod(String paymentM) {
		for (PaymentMethod e : PaymentMethod.values()) {
			if (e.paymentMethod.equals(paymentM))
				return e;
		}
		return null;
	}
}
