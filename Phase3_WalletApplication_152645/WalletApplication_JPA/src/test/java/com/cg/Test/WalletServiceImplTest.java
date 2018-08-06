package com.cg.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.TreeMap;

import org.junit.Test;

import com.cg.WalletApplication.Exception.BankException;
import com.cg.WalletApplication.bean.Customer;
import com.cg.WalletApplication.bean.Wallet;
import com.cg.WalletApplication.service.IWalletService;
import com.cg.WalletApplication.service.WalletServiceImpl;

public class WalletServiceImplTest {
	public static IWalletService iWalletService=new WalletServiceImpl();
    @Test
	public void addCustomerTestTrue() throws BankException
	{
		Customer customer1 = new Customer("9949453482","Varun","Varun@123","pulivarun125@gmail.com",new Wallet());
		assertEquals("9949453482",iWalletService.addCustomer(customer1));
			
	}
    @Test
  	public void addCustomerTestFalse() throws BankException
  	{

  		Customer customer2 = new Customer("8885599774","Vineeth","Vineeth@123","balusanivineeth8@gmail.com",new Wallet());
  		assertNotEquals("56968621",iWalletService.addCustomer(customer2));
  		
  	}
	

	@Test
	public void initBalanceTest() throws BankException
	{
		Customer customer1 = new Customer("9949453482","Varun","Varun@123","pulivarun125@gmail.com",new Wallet());
		iWalletService.addCustomer(customer1);
		assertEquals(BigDecimal.valueOf(0.0),customer1.getWallet().getBalance());
		
	}
	
	@Test
	public void depositMoneyTest() throws BankException, ClassNotFoundException, SQLException
	{
		Customer customer2 = new Customer("8885599774","Vineeth","Vineeth@123","balusanivineeth8@gmail.com",new Wallet());
		iWalletService.addCustomer(customer2);
		iWalletService.deposit(customer2, BigDecimal.valueOf(8500.00));
		Customer result = iWalletService.showBalance("8885599774", "Vineeth@123");
		assertEquals(BigDecimal.valueOf(8500.00),result.getWallet().getBalance());
	}
	@Test
	public void withdrawMoneyTestTrue() throws BankException, ClassNotFoundException, SQLException
	{
		Customer customer2 = new Customer("8885599774","Vineeth","Vineeth@123","balusanivineeth8@gmail.com",new Wallet());
		iWalletService.addCustomer(customer2);
		iWalletService.deposit(customer2, BigDecimal.valueOf(8500.00));
		assertTrue(iWalletService.withDraw(customer2, BigDecimal.valueOf(3000.00)));
	}

	
	@Test(expected = BankException.class)
	public void withdrawMoneyTestFalse() throws BankException, ClassNotFoundException, SQLException
	{
		Customer customer2 = new Customer("8885599774","Vineeth","Vineeth@123","balusanivineeth8@gmail.com",new Wallet());
		iWalletService.addCustomer(customer2);
		iWalletService.deposit(customer2, BigDecimal.valueOf(8500.00));
		assertFalse(iWalletService.withDraw(customer2, BigDecimal.valueOf(9000.00)));
	}

}
