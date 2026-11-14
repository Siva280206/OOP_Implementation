package com.BankingApp.demo;

public class CardPayment implements Payment {
	private int cardNO;
	CardPayment(int cardNO){
		this.cardNO=cardNO;
	}
	@Override
	public void pay(BankAccount account, double amount) {
		System.out.println("Paying via card"+cardNO);
		account.withDraw(amount);
	}
}
