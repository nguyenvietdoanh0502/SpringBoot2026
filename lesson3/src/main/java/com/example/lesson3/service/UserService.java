package com.example.lesson3.service;

import com.example.lesson3.dto.request.CreateUserRequest;
import com.example.lesson3.exception.DuplicateResourceException;
import com.example.lesson3.exception.ResourceNotFoundException;
import com.example.lesson3.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    public List<User> findAll(){
        return users;
    }

    public User findById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
    public User create(CreateUserRequest request) {
        // Kiểm tra email trùng
        boolean emailExists = users.stream()
                .anyMatch(u -> u.getEmail().equals(request.getEmail()));
        if (emailExists) {
            throw new DuplicateResourceException("User", "email", request.getEmail());
        }

        User user = new User();
        user.setId(nextId++);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        users.add(user);
        return user;
    }
    public void delete(Long id) {
        User user = findById(id);
        users.remove(user);
    }
}
