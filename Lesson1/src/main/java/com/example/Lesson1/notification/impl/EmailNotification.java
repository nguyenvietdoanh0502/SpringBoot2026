package com.example.Lesson1.notification.impl;

import com.example.Lesson1.notification.INotificationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EmailNotification implements INotificationService {
    @Override
    public void sendNotification(String to, String message) {
        System.out.println("Đã gửi email: "+message+" đến "+to);
    }
}
