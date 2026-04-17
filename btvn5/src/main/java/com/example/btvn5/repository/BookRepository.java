package com.example.btvn5.repository;


import com.example.btvn5.entity.Book;
import com.example.btvn5.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    boolean existsByIsbn(String isbn);
    Page<Book> findByAuthorId(Long authorId, Pageable pageable);
    Page<Book> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Book> findByCategory(Category category, Pageable pageable);
    boolean existsByAuthorId(Long authorId);
    List<Book> getBookByAuthorId(Long authorId);
    Book findBookById(Long id);
}
