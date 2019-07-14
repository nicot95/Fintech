package com.nico.pagantis;

import com.nico.pagantis.model.*;
import com.nico.pagantis.service.TransferAgentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PagantisApplicationTests {

	private static TransferAgentService transferAgentService;
	private static TransferAgentService successFulTransferAgentService;
	private static TransferAgentService failingTransferAgentService;
	private static Account santanderAccount1;
	private static Account santanderAccount2;
	private static Account bbvaAccount;

	@Before
	public void setup() {
		transferAgentService = new TransferAgentService(new Random());

		Bank santander = new Bank(1, "Santander");
		Bank bbva = new Bank(2, "BBVA");

		Customer santanderCustomer1 = new Customer(1, santander);
		Customer santanderCustomer2 = new Customer(2, santander);
		Customer bbvaCustomer = new Customer(1, bbva);

		santanderAccount1 = new Account(1, santanderCustomer1, new Money(Money.Currency.EURO, 10000), new ArrayList<>());
		santanderAccount2 = new Account(2, santanderCustomer2, new Money(Money.Currency.EURO, 5000), new ArrayList<>());
		bbvaAccount = new Account(3, bbvaCustomer, new Money(Money.Currency.EURO, 3000), new ArrayList<>());

		Random successfulMockedRandom = Mockito.mock(Random.class);
		Random failingMockedRandom = Mockito.mock(Random.class);

		successFulTransferAgentService = new TransferAgentService(successfulMockedRandom);
		failingTransferAgentService = new TransferAgentService(failingMockedRandom);

		when(successfulMockedRandom.nextFloat()).thenReturn(1f);
		when(failingMockedRandom.nextFloat()).thenReturn(0f);
	}

	@Test
	public void intraBankTransfer_success() {
		boolean successful = transferAgentService.processTransfer(santanderAccount1, santanderAccount2, new Money(Money.Currency.EURO, 300));

		Assert.assertThat(santanderAccount1.getMoney().getAmount(), is(9700L));
		Assert.assertThat(santanderAccount2.getMoney().getAmount(), is(5300L));
		Assert.assertThat(successful, is(true));
	}

	@Test
	public void interBankTransfer_success() {
		boolean successful = successFulTransferAgentService.processTransfer(santanderAccount1, bbvaAccount, new Money(Money.Currency.EURO, 300));

		Assert.assertThat(santanderAccount1.getMoney().getAmount(), is(9695L));
		Assert.assertThat(bbvaAccount.getMoney().getAmount(), is(3300L));
		Assert.assertThat(successful, is(true));
	}

	@Test
	public void interBankTransfer_failure() {
		boolean successful = failingTransferAgentService.processTransfer(santanderAccount1, bbvaAccount, new Money(Money.Currency.EURO, 300));

		Assert.assertThat(santanderAccount1.getMoney().getAmount(), is(10000L));
		Assert.assertThat(bbvaAccount.getMoney().getAmount(), is(3000L));
		Assert.assertThat(successful, is(false));
	}

	@Test(expected = ExceededLimitException.class)
	public void interBankTransfer_overlimit() {
		boolean successful = successFulTransferAgentService.processTransfer(santanderAccount1, bbvaAccount, new Money(Money.Currency.EURO, 1200));
	}

}
