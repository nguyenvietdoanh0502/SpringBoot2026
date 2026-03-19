package com.example.Lesson1.payment.impl;

import com.example.Lesson1.payment.IPaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class CashPayment implements IPaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán thành công số tiền: "+amount);
    }

    @Override
    public String getMethodName() {
        return "Cash";
    }
}
