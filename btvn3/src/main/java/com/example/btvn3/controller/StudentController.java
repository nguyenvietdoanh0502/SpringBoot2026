package com.example.btvn3.controller;

import com.example.btvn3.dto.request.CreateStudentRequest;
import com.example.btvn3.dto.request.UpdateStudentRequest;
import com.example.btvn3.dto.response.ApiResponse;
import com.example.btvn3.model.Student;
import com.example.btvn3.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudent(){
        return ResponseEntity.ok(ApiResponse.success(studentService.read()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@Valid @PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(studentService.findById(id)));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@Valid @RequestBody CreateStudentRequest request){
        Student student = studentService.create(request);
        return ResponseEntity.ok(ApiResponse.created(student));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> updateStudent(@Valid @RequestBody UpdateStudentRequest request, @Valid @PathVariable Long id){
        Student student = studentService.update(request,id);
        return ResponseEntity.ok(ApiResponse.success(student));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStudent(@Valid @PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.ok(ApiResponse.deleted());
    }
    @GetMapping("/major/{major}")
    public ResponseEntity<ApiResponse<List<Student>>> getStudentsByMajor(@Valid @PathVariable String major){
        List<Student> res = studentService.getStudentsByMajor(major);
        return  ResponseEntity.ok(ApiResponse.success(res));
    }
    @GetMapping("/honors")
    public ResponseEntity<ApiResponse<List<Student>>> getStudentsbyGPA(){
        List<Student> res = studentService.getStudentsByGPA();
        return ResponseEntity.ok(ApiResponse.success(res));
    }
}
