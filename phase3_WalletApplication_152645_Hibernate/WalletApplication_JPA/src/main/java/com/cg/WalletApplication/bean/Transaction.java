package com.cg.WalletApplication.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="Transactions")
public class Transaction implements Serializable,Comparable<Transaction>
{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private  int transactionId;
	@ManyToOne
	@JoinColumn(name="mobile_no")
	private Customer customer;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	@Column(name="Date_Of_Trans",length=20)
	private Timestamp dateOfTrans;
	@Column(name="Type_Of_Trans",length=12)
	private String type;
	@Column(name="Amount", length=16)
	private BigDecimal amount;
	@Column(name="Balance", length=16)
	private BigDecimal balance;
	
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public Timestamp getDateOfTrans() {
		return dateOfTrans;
	}
	public void setDateOfTrans(Timestamp dateOfTrans) {
		this.dateOfTrans = dateOfTrans;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int compareTo(Transaction o) {
		
		return this.transactionId-o.transactionId;
	}
	

	
}
