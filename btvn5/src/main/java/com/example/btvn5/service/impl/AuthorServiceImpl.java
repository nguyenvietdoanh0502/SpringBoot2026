package com.example.btvn5.service.impl;

import com.example.btvn5.dto.request.CreateAuthorRequest;
import com.example.btvn5.entity.Author;
import com.example.btvn5.entity.Book;
import com.example.btvn5.exception.BadRequestException;
import com.example.btvn5.exception.DuplicateResourceException;
import com.example.btvn5.exception.ResourceNotFoundException;
import com.example.btvn5.repository.AuthorRepository;
import com.example.btvn5.repository.BookRepository;
import com.example.btvn5.service.AuthorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository,BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
    @Transactional
    @Override
    public Author creatAuthor(CreateAuthorRequest request) {
        boolean isExist = authorRepository.existsByEmail(request.getEmail());
        if(isExist){
            throw new DuplicateResourceException("Author","email",request.getEmail());
        }
        Author author = new Author();
        author.setName(request.getName());
        author.setPhone(request.getPhone());
        author.setEmail(request.getEmail());
        authorRepository.save(author);
        return author;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author","id",id));
    };


    @Transactional
    @Override
    public Author updateAuthorById(Long id, CreateAuthorRequest update) {
        Author author = getAuthorById(id);
        author.setName(update.getName());
        author.setPhone(update.getPhone());
        author.setEmail(update.getEmail());
        authorRepository.save(author);
        return author;
    }

    @Transactional
    @Override
    public void deleteAuthor(Long id) {
        if(bookRepository.existsByAuthorId(id)){
            throw new BadRequestException("Tác giả vẫn còn sách, không thể xóa");
        }
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAuthorBooks(Long id) {
        return bookRepository.getBookByAuthorId(id);
    }
}
