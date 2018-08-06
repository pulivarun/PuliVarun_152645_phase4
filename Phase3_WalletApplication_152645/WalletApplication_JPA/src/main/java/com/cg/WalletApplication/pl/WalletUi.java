package com.cg.WalletApplication.pl;

import java.math.BigDecimal;
import java.util.Scanner;
import com.cg.WalletApplication.Exception.BankException;
import com.cg.WalletApplication.bean.Customer;
import com.cg.WalletApplication.service.IWalletService;
import com.cg.WalletApplication.service.WalletServiceImpl;

public class WalletUi {

	public static void main(String[] args) {
		IWalletService iWalletService = new WalletServiceImpl();
		Scanner scanner = new Scanner(System.in);

		int choice;
		try {
			do {
				System.out.println(
						"1) Create Account \n 2) Show Balance \n 3) Deposit\n 4) Withdraw \n 5)Fund Transfer\n 6) Print Transactions\n 7) exit");
				System.out.println("Enter Your Choice");
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					try {
						Customer customer = new Customer();
						System.out.println("Enter Customer  Name");
						String name = scanner.next();
						iWalletService.checkName(name);
						customer.setName(name);
						System.out.println("Enter Mobile Number");
						String mobileNumber = scanner.next();
						iWalletService.checkMobileNumber(mobileNumber);
						customer.setMobileNumber(mobileNumber);
						System.out.println("Enter Password");
						String password = scanner.next();
						iWalletService.checkPassword(password);
						customer.setPassword(password);
						System.out.println("Enter Email Id");
						String emailId = scanner.next();
						iWalletService.checkEmail(emailId);
						customer.setEmailId(emailId);
						String result = iWalletService.addCustomer(customer);
						if (result != null)
							System.out.println("Account created with Account Number " + result);
					} catch (BankException bankException) {
						System.out.println(bankException.getMessage());
					}

					break;
				case 2:

					try {
						System.out.println("Enter your mobile number");
						String mobileNum = scanner.next();
						iWalletService.checkMobileNumber(mobileNum);
						System.out.println("Enter the password");
						String password = scanner.next();
						iWalletService.checkPassword(password);
						Customer customer = iWalletService.showBalance(mobileNum, password);
						if (customer != null) {
							System.out.println("Available balance is " + customer.getWallet().getBalance());
						} 
					} catch (BankException bankException) {

						System.out.println(bankException.getMessage());
					}
					break;
				case 3:
					try {
						System.out.println("Enter your mobile number");
						String mobileNum = scanner.next();
						iWalletService.checkMobileNumber(mobileNum);
						System.out.println("Enter the password");
						String password = scanner.next();
						iWalletService.checkPassword(password);
						Customer customer = iWalletService.check(mobileNum, password);
						if (customer != null) {
							System.out.println("Enter amount to deposit ");
							BigDecimal amount = scanner.nextBigDecimal();
							iWalletService.deposit(customer, amount);
							System.out.println("Deposited");
						} 
					} catch (BankException bankException) {

						System.out.println(bankException.getMessage());
					}

					break;
				case 4:
					try {
						System.out.println("Enter your mobile number");
						String mobileNum = scanner.next();
						iWalletService.checkMobileNumber(mobileNum);
						System.out.println("Enter the password");
						String password = scanner.next();
						iWalletService.checkPassword(password);
						Customer customer = iWalletService.check(mobileNum, password);
						if (customer != null) {
							System.out.println("Enter the amount to withdraw");
							BigDecimal amount = scanner.nextBigDecimal();
							boolean result = iWalletService.withDraw(customer, amount);
							if (result == true) {
								System.out.println("Amount is succesfully withdrawn and current balance is "
										+ customer.getWallet().getBalance());	
							}
						} 
					} catch (BankException bankException) {
						System.out.println(bankException.getMessage());
					}
					break;
				case 5:
					try {
						System.out.println("Enter Receivers mobile number ");
						String receiverMobile = scanner.next();
						iWalletService.checkMobileNumber(receiverMobile);
						boolean result = iWalletService.isFound(receiverMobile);
						if (result) {
							System.out.println("Enter sender mobile number");
							String senderMobile = scanner.next();
							iWalletService.checkMobileNumber(senderMobile);
							System.out.println("Enter senders password");
							String password = scanner.next();
							iWalletService.checkPassword(password);
							Customer customer = iWalletService.check(senderMobile, password);
							if (customer != null) {
								System.out.println("Enter the amount to transfer");
								BigDecimal amount = scanner.nextBigDecimal();
								boolean output = iWalletService.transfer(senderMobile, receiverMobile, amount);
								if (output == true) {
									System.out.println("Amount is succesfully transferred to "+receiverMobile+" and current balance is "
											+ customer.getWallet().getBalance());
								} 

							}
						} 

					} catch (BankException bankException) {
						System.out.println(bankException.getMessage());
					} catch (InterruptedException interruptedException) {
						System.out.println(interruptedException.getMessage());
					}
					break;
			case 6:
					try {
						System.out.println("Enter your mobile number");
						String mobileNum = scanner.next();
						iWalletService.checkMobileNumber(mobileNum);
						System.out.println("Enter the password");
						String password = scanner.next();
						iWalletService.checkPassword(password);
						Customer customer = iWalletService.check(mobileNum, password);
						if (customer != null) {
                              String mytransactions=iWalletService.printTransactions(customer);
                             Scanner sc = new Scanner(mytransactions).useDelimiter(",");
                             System.out.println("   Date       Time        Cr/Db    Amount");
                             while(sc.hasNext())
                             {
                            	 System.out.println(sc.next());
                            	 System.out.println("-----------------------------------------");
                             }
                       }

					} catch (BankException bankException) {
						System.out.println(bankException.getMessage());
					}
					break;
				case 7:
					scanner.close();
					System.exit(0);
					break;
				default:
					System.out.println("Enter your choice correctly");
					break;

				}
			} while (choice != 7);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}
	}

}
