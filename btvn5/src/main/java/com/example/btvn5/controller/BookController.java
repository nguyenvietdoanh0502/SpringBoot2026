package com.example.btvn5.controller;

import com.example.btvn5.dto.request.CreateBookRequest;
import com.example.btvn5.dto.response.ApiResponse;
import com.example.btvn5.entity.Book;
import com.example.btvn5.entity.Category;
import com.example.btvn5.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> createBook(@Valid @RequestBody CreateBookRequest request){
        Book book = bookService.createBook(request);
        return ResponseEntity.ok(ApiResponse.created(book));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<Page<Book>>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Book> bookPage = bookService.getBooks(page,size);
        return ResponseEntity.ok(ApiResponse.success(bookPage));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@Validated @PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(bookService.getBookById(id)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBookById(@Validated @PathVariable Long id, @Valid @RequestBody CreateBookRequest update){
        Book book = bookService.updateBookById(id,update);
        return ResponseEntity.ok(ApiResponse.created(book));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@Validated @PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok(ApiResponse.deleted());
    }
    @PostMapping("/{id}/borrow")
    public ResponseEntity<ApiResponse<Book>> borrowBook(@Validated @PathVariable Long id){
        Book book = bookService.borrowBook(id);
        return  ResponseEntity.ok(ApiResponse.success(book));
    }
    @PostMapping("/{id}/return")
    public ResponseEntity<ApiResponse<Book>> returnBook(@Validated @PathVariable Long id){
        Book book = bookService.returnBook(id);
        return ResponseEntity.ok(ApiResponse.success(book));
    }
    @GetMapping("/search?keyword=")
    public ResponseEntity<ApiResponse<Page<Book>>> searchBookByTitle(@Validated @RequestParam String keyword,@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size){
        Page<Book> bookPage = bookService.searchBookByTitle(keyword,page,size);
        return ResponseEntity.ok(ApiResponse.success(bookPage));
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<Page<Book>>> searchBookByTitle(@Validated @PathVariable Category category, @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size){
        Page<Book> bookPage = bookService.filterBookByCategory(category,page,size);
        return ResponseEntity.ok(ApiResponse.success(bookPage));
    }
}
