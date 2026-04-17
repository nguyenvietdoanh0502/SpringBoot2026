package com.example.btvn5.repository;


import com.example.btvn5.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Author getAuthorById(Long id);
}
