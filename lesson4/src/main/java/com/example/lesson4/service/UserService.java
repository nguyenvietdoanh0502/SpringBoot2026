package com.example.lesson4.service;


import com.example.lesson4.dto.request.CreateUserRequest;
import com.example.lesson4.entity.User;
import com.example.lesson4.exception.DuplicateResourceException;
import com.example.lesson4.exception.ResourceNotFoundException;
import com.example.lesson4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public User create(CreateUserRequest request) {
        // Kiểm tra email trùng
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User", "email", request.getEmail());
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .age(request.getAge())
                .build();

        return userRepository.save(user);  // INSERT vào database
    }

    public User update(Long id, CreateUserRequest request) {
        User user = findById(id);
        userRepository.findByEmail(request.getEmail())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new DuplicateResourceException("User", "email", request.getEmail());
                    }
                });

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());

        return userRepository.save(user);  // UPDATE trong database
    }

    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);  // DELETE từ database
    }

    public List<User> searchByName(String keyword) {
        return userRepository.findByNameContaining(keyword);
    }
}
