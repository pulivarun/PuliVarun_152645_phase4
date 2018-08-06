package com.cg.WalletApplication.dao;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.cg.WalletApplication.Exception.BankException;
import com.cg.WalletApplication.bean.Customer;

public interface IWalletDao {


	String addCustomer(Customer customer);

	void beginTransaction();

	void commitTransaction();

	Customer showBalance(String mobileNum, String password);

	Customer findCustomer(String mobileNum, String password);

	void deposit(Customer customer, BigDecimal amount) throws SQLException, ClassNotFoundException, BankException;

	boolean withdraw(Customer customer, BigDecimal amount) throws ClassNotFoundException, SQLException, BankException;

	boolean customerExists(String receiverMobile);

	boolean transfer(String senderMobile, String receiverMobile, BigDecimal amount) throws ClassNotFoundException, SQLException, BankException;

	String printTransactions(Customer customer) throws ClassNotFoundException, SQLException;

	

}
