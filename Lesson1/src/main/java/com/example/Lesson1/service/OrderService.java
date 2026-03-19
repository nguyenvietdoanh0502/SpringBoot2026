package com.example.Lesson1.service;

import com.example.Lesson1.notification.INotificationService;
import com.example.Lesson1.payment.IPaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {
    private final IPaymentMethod paymentMethod;
    private final INotificationService notificationService;

    @Autowired
    public OrderService(IPaymentMethod paymentMethod, INotificationService notificationService) {
        this.paymentMethod = paymentMethod;
        this.notificationService = notificationService;
    }

    public void processOrder(String customer, String product, double amount){
        paymentMethod.pay(amount);
        String method = paymentMethod.getMethodName();
        String message = "Khách hàng "+customer+" đã thanh toán "+amount+" cho sản phẩm "+product+" qua hình thức "+method;
        notificationService.sendNotification(customer,message);
    }
}
