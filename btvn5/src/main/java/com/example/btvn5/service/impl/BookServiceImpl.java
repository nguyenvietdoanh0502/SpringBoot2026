package com.example.btvn5.service.impl;

import com.example.btvn5.dto.request.CreateBookRequest;
import com.example.btvn5.entity.Author;
import com.example.btvn5.entity.Book;
import com.example.btvn5.entity.Category;
import com.example.btvn5.entity.Status;
import com.example.btvn5.exception.BadRequestException;
import com.example.btvn5.exception.DuplicateResourceException;
import com.example.btvn5.exception.ResourceNotFoundException;
import com.example.btvn5.repository.AuthorRepository;
import com.example.btvn5.repository.BookRepository;
import com.example.btvn5.service.BookService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public Book createBook(CreateBookRequest request) {
        Author author = authorRepository.findById(request.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author","Id",request.getAuthorId()));
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateResourceException("Book","Isbn",request.getIsbn());
        }
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setCategory(request.getCategory());
        book.setStatus(request.getStatus());
        book.setTotalCopies(request.getTotalCopies());
        book.setAvailableCopies(request.getTotalCopies());
        book.setPublishedYear(request.getPublishedYear());
        book.setAuthor(author);
        bookRepository.save(book);
        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Book> getBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return bookRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Transactional
    @Override
    public Book updateBookById(Long id, CreateBookRequest update) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book","Id",id));
        Author author = authorRepository.findById(update.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author","Id",update.getAuthorId()));
        Book book = getBookById(id);
        book.setTitle(update.getTitle());
        book.setIsbn(update.getIsbn());
        book.setCategory(update.getCategory());
        book.setStatus(update.getStatus());
        book.setTotalCopies(update.getTotalCopies());
        book.setAvailableCopies(update.getTotalCopies());
        book.setPublishedYear(update.getPublishedYear());
        book.setAuthor(author);
        bookRepository.save(book);
        return book;
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findBookById(id);
        bookRepository.delete(book);
    }

    @Transactional
    @Override
    public Book borrowBook(Long id) {
        Book book = bookRepository.findBookById(id);
        if(book!=null){
            if(book.getStatus().equals("DISCONTINUED")){
                throw new BadRequestException("Sách đã ngừng lưu hành");
            }
            if(book.getAvailableCopies()==0){
                throw new BadRequestException("Sách đã hết");
            }
            book.setAvailableCopies(book.getAvailableCopies()-1);
            if(book.getAvailableCopies()==0){
                book.setStatus(Status.valueOf("OUT_OF_STOCK"));
            }
            bookRepository.save(book);

        }
        else{
            throw new ResourceNotFoundException("Book","id",id);
        }
        return book;
    }

    @Transactional
    @Override
    public Book returnBook(Long id) {
        Book book = bookRepository.findBookById(id);
        if(book!=null){
            if(book.getAvailableCopies()==book.getTotalCopies()){
                throw new BadRequestException("Sách đã đủ không thể trả thêm");

            }
            book.setAvailableCopies(book.getAvailableCopies()+1);
            bookRepository.save(book);
        }
        else{
            throw new ResourceNotFoundException("Book","id",id);
        }
        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Book> searchBookByTitle(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return bookRepository.findByTitleContainingIgnoreCase(keyword,pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Book> filterBookByCategory(Category category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return bookRepository.findByCategory(category,pageable);
    }
}
