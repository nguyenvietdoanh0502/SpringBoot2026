package com.example.Lesson1.notification.impl;

import com.example.Lesson1.notification.INotificationService;
import org.springframework.stereotype.Component;

@Component
public class SmsNotification implements INotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Đã gửi tin nhắn sms: "+message+" đến "+to);
    }
}
