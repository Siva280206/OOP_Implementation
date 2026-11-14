package com.BankingApp.demo;

public abstract class BankAccount {
	private int accID;
	private double balance;
	private Customer customer;
	public BankAccount(int accID, double balance, Customer customer) {
		this.accID = accID;
		this.balance  = balance;
		this.customer = customer;
	}
	public void deposit(double amount) {
		balance+=amount;
		System.out.println("Successfull Deposit"+"Current Balance: "+balance);
	}
	public void withDraw(double amount) {
		balance-=amount;
		System.out.println("Successfull Withdraw"+"Current Balance: "+balance);
	}
	public void checkBalance() {
		System.out.println("Account ID: "+accID);
		System.out.println("Balance: "+balance+"of customer: "+customer);
	}
	public int getAccNo() {
		return accID;
	}
	public double getBalance() {
		return balance;
	}
	public abstract void calculateInterest();
}
