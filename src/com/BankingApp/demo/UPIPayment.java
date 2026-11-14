package com.BankingApp.demo;

public class UPIPayment implements Payment {

    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(BankAccount account, double amount) {

        System.out.println("Processing UPI Payment...");
        System.out.println("UPI ID: " + upiId);

        account.withDraw(amount);
    }
}