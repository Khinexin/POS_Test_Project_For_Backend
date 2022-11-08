package com.mytest.pos.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.mytest.pos.exception.DataNotFoundException;
import com.mytest.pos.exception.InvalidInputException;
import com.mytest.pos.model.PaymentMethod;
import com.mytest.pos.model.Sales;
import com.mytest.pos.model.dto.DateSalesPoint;
import com.mytest.pos.model.dto.OutputPriceAndPoint;
import com.mytest.pos.repository.SalesRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SalesService implements ISalesService {

	private final SalesRepository salesRepository;

//	private final String TEMP_SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
//
//	SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//	SimpleDateFormat outputDateFormat = new SimpleDateFormat(TEMP_SIMPLE_DATE_FORMAT);

	@Override
	public Sales findById(int id) throws DataNotFoundException {
		return salesRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Not Found Sale Record with id = " + id));
	}

	@Override
	public List<Sales> findAll() {
		return salesRepository.findAll();
	}

	@Override
	public List<DateSalesPoint> findByDatetimeBetween(String fromDate, String toDate) throws DataNotFoundException {
		System.out.println(">>>>> query findByDatetimeBetween " + fromDate + " - " + toDate);
		List<DateSalesPoint> dateSalesPoint = new ArrayList<DateSalesPoint>();
		Date tempFromDate = new Date();
		Date tempToDate = new Date();

		try {

			tempFromDate = Date.from(OffsetDateTime.parse(fromDate).toInstant());
			tempToDate = Date.from(OffsetDateTime.parse(toDate).toInstant());

		} catch (Exception e) {
			throw new InvalidInputException("Invalid date !" );
		}
			List<Sales> salesList = salesRepository.findByDatetimeBetween(tempFromDate, tempToDate);

			salesList.forEach(a -> {
				dateSalesPoint.add(DateSalesPoint.builder().datetime(String.valueOf(a.getDatetime()))
						.sales(String.valueOf(a.getSales())).point(a.getFinalPoint()).build());
			});

			return dateSalesPoint;

		

	}

	@Override
	public OutputPriceAndPoint addSales(String price, Float price_modifier, String payment_method, String datetime) {

		System.out.println(
				">>>>> query addSales " + price + " - " + price_modifier + " - " + payment_method + " - " + datetime);

		PaymentMethod paymentMethod = PaymentMethod.getEnumByPaymentMethod(payment_method);

		if (Objects.isNull(paymentMethod)) {
			throw new InvalidInputException("Invalid payment method !");
		} else {

			Date convertedDate = new Date();

			try {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
				convertedDate = sdf.parse(datetime);

			} catch (Exception e) {
				throw new InvalidInputException("Invalid datetime !" );
			}
			BigDecimal final_price = new BigDecimal(Double.valueOf(price) * price_modifier).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			int final_point = (int) Math.round(Double.valueOf(price) * paymentMethod.getModifyPoint());

			Sales sales = salesRepository
					.save(Sales.builder().sales(new BigDecimal(price)).priceModifier(BigDecimal.valueOf(price_modifier))
							.paymentMethod(paymentMethod).datetime(convertedDate)

							.finalPrice(String.valueOf(final_price)).finalPoint(final_point).build());

			System.out.println("Successfully Save Sales Record!");
			return OutputPriceAndPoint.builder().final_price(sales.getFinalPrice()).points(sales.getFinalPoint())
					.build();

		}
	}

}
