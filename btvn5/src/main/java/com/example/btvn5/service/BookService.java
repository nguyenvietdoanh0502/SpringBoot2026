package com.example.btvn5.service;

import com.example.btvn5.dto.request.CreateBookRequest;
import com.example.btvn5.entity.Book;
import com.example.btvn5.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    public Book createBook(CreateBookRequest request);
    public Page<Book> getBooks(int page, int size);
    public Book getBookById(Long id);
    public Book updateBookById(Long id, CreateBookRequest update);
    public void deleteBook(Long id);
    public Book borrowBook(Long id);
    public Book returnBook(Long id);
    public Page<Book> searchBookByTitle(String keyword, int page, int size);
    public Page<Book> filterBookByCategory(Category category, int page, int size);
}
