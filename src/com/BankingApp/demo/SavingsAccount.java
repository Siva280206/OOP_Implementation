package com.BankingApp.demo;

public class SavingsAccount extends BankAccount {
	private int interestRate;
	public SavingsAccount(int accID, double balance, Customer customer, int interestRate) {
		super(accID,balance,customer);
		this.interestRate=interestRate;
	}
	@Override
	public void calculateInterest() {
		System.out.println("Interest is: "+(getBalance()*interestRate/100));
	}
}
