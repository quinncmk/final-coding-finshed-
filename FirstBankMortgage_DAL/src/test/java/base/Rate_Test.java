package base;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import domain.RateDomainModel;

public class Rate_Test {
	private RateDAL rateTest;

	@Test
	public void Test(){
	/*
	private static RateDomainModel rate1;
	private static RateDomainModel rate2;
	private static RateDomainModel rate3;
	private static RateDomainModel rate4;
	
	@BeforeClass
	public static void rateInstance() throws Exception{
		rate1.setRateID(95);
		rate1.setMinCreditScore(500);
		rate1.setInterestRate(2.25);
		
		rate2.setRateID(96);
		rate2.setMinCreditScore(600);
		rate2.setInterestRate(3.25);
		
		rate2.setRateID(97);
		rate2.setMinCreditScore(700);
		rate2.setInterestRate(4.25);
		
		rate2.setRateID(98);
		rate2.setMinCreditScore(800);
		rate2.setInterestRate(5.25);
	}
	*/
		assertTrue(rateTest.getRate(650).getInterestRate() == 3.75);
		assertTrue(rateTest.getRate(700).getInterestRate() == 4.55);
		assertTrue(rateTest.getRate(750).getInterestRate() == 2.85);
		assertTrue(rateTest.getRate(800).getInterestRate() == 3.45);
	}
}