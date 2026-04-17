package com.example.btvn5.service;

import com.example.btvn5.dto.request.CreateAuthorRequest;
import com.example.btvn5.entity.Author;
import com.example.btvn5.entity.Book;

import java.util.List;

public interface AuthorService {
    public Author creatAuthor(CreateAuthorRequest request);
    public List<Author> getAuthors();
    public Author getAuthorById(Long id);
    public Author updateAuthorById(Long id, CreateAuthorRequest update);
    public void deleteAuthor(Long id);
    public List<Book> getAuthorBooks(Long id);
}
