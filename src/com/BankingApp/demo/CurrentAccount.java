package com.BankingApp.demo;

public class CurrentAccount extends BankAccount {
	private double minimumBalance;
	public CurrentAccount(int accID, double balance, Customer customer,double minimumBalance) {
		super(accID,balance,customer);
		this.minimumBalance=minimumBalance;
	}
	@Override
	public void withDraw(double amount) {
		if(amount>0&&amount<minimumBalance) {
			super.withDraw(amount);
			System.out.println("WithDraw Success!!");
		}
		else {
			System.out.println("Required Balance not available !!");
		}
	}
	@Override
	public void calculateInterest() {
		System.out.println("This is Current Account !!!");
	}
}
