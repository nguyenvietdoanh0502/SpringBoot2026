package com.example.Lesson1.payment;

public interface IPaymentMethod {
    public void pay(double amount);
    public String getMethodName();
}
