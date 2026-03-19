package com.example.Lesson1;

import com.example.Lesson1.notification.impl.EmailNotification;
import com.example.Lesson1.notification.impl.SmsNotification;
import com.example.Lesson1.payment.impl.BankTransferPayment;
import com.example.Lesson1.payment.impl.CashPayment;
import com.example.Lesson1.payment.impl.MoMoPayment;
import com.example.Lesson1.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Lesson1Application {

	public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example");

        MoMoPayment moMoPayment = context.getBean(MoMoPayment.class);
        CashPayment cashPayment = context.getBean(CashPayment.class);
        BankTransferPayment bankTransferPayment = context.getBean(BankTransferPayment.class);

        EmailNotification emailNotification = context.getBean(EmailNotification.class);
        SmsNotification smsNotification = context.getBean(SmsNotification.class);
        OrderService order1 = new OrderService(moMoPayment,smsNotification);
        OrderService order2 = new OrderService(cashPayment,emailNotification);
        order1.processOrder("Viet Doanh","Dien thoai",20000);
        order2.processOrder("Ne Lam","Cut",30000);

	}

}
