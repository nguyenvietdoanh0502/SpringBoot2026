package com.example.btvn5.controller;

import com.example.btvn5.dto.request.CreateAuthorRequest;
import com.example.btvn5.dto.response.ApiResponse;
import com.example.btvn5.entity.Author;
import com.example.btvn5.entity.Book;
import com.example.btvn5.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Author>> createAuthor(@Valid @RequestBody CreateAuthorRequest request){
        Author author = authorService.creatAuthor(request);
        return ResponseEntity.ok(ApiResponse.created(author));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Author>>> getAuthors(){
        return ResponseEntity.ok(ApiResponse.success(authorService.getAuthors()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> getAuthorById(@Validated @PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(authorService.getAuthorById(id)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Author>> updateAuthor(@Validated @PathVariable Long id, @Valid @RequestBody CreateAuthorRequest update){
        Author author = authorService.updateAuthorById(id,update);
        return ResponseEntity.ok(ApiResponse.success(author));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAuthor(@Validated @PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.ok(ApiResponse.deleted());
    }
    @GetMapping("/{id}/books")
    public ResponseEntity<ApiResponse<List<Book>>> getBooksByAuthor(@Validated @PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(authorService.getAuthorBooks(id)));
    }

}
