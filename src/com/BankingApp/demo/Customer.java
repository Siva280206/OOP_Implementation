package com.BankingApp.demo;
import java.util.ArrayList;
import java.util.List;
public class Customer {
	private int Cid;
	private String Cname;
	private String Cmail;
	private List<BankAccount> accounts = new ArrayList<>();
	
	public Customer(int Cid, String Cname, String Cmail){
		this.Cid = Cid;
		this.Cname = Cname;
		this.Cmail = Cmail;
	}
	public int getCid() {
		return Cid;
	}
	public String getCname() {
		return Cname;
	}
	public String getCmail() {
		return Cmail;
	}
	public void addAccount(BankAccount a) {
		accounts.add(a);
		System.out.println("Account added");
	}
	public List<BankAccount> showAcc() {
		return accounts;
	}
	public void displayCustomer() {
		System.out.println("Customer ID"+Cid+"Customer Name"+Cname+"Mail"+Cmail);
	}
	
}
