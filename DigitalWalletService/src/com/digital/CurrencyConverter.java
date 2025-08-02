package com.digital;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

	private static final Map<com.digital.Cuurency,BigDecimal> exchnageRates=new HashMap<>();
	
	static {
		exchnageRates.put(Cuurency.USD,BigDecimal.ONE);
		exchnageRates.put(Cuurency.EUR,new BigDecimal(".85"));
		exchnageRates.put(Cuurency.JPY,new BigDecimal("110.00"));
		exchnageRates.put(Cuurency.GBP,new BigDecimal(".72"));
	}
	
	public static BigDecimal conver(BigDecimal amount,Cuurency sourceCurrency,Cuurency destinationCuurency) {
		BigDecimal sourceRate=exchnageRates.get(sourceCurrency);
		BigDecimal destinationRate=exchnageRates.get(destinationCuurency);
		return amount.multiply(sourceRate).divide(destinationRate,RoundingMode.HALF_UP);
	}
}
