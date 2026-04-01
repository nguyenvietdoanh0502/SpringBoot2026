package com.example.btvn3.service;

import com.example.btvn3.dto.request.CreateStudentRequest;
import com.example.btvn3.dto.request.UpdateStudentRequest;
import com.example.btvn3.exception.DuplicateResourceException;
import com.example.btvn3.exception.ResourceNotFoundException;
import com.example.btvn3.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private Long nextId = 1L;
    public List<Student> read(){
        return students;
    }
    public Student findById(Long id) {
        return students.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));
    }
    public Student create(CreateStudentRequest request){
        boolean studentCodeExists = students.stream().anyMatch(u->u.getStudentCode().equals(request.getStudentCode()));
        boolean emailExists = students.stream().anyMatch(u->u.getEmail().equals(request.getEmail()));
        if(studentCodeExists){
            throw new DuplicateResourceException("Student","student code",request.getStudentCode());
        }if (emailExists) {
            throw new DuplicateResourceException("Student", "email", request.getEmail());
        }
        Student student = new Student();
        student.setId(nextId++);
        student.setStudentCode(request.getStudentCode());
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGpa(request.getGpa());
        student.setMajor(request.getMajor());
        student.setYear(request.getYear());
        students.add(student);
        return student;
    }
    public Student update(UpdateStudentRequest request, Long id){
        Student student = findById(id);
        boolean emailExists = false;
        for(var x: students){
            if(x.getEmail().equals(request.getEmail()) && !x.getId().equals(student.getId())){
                emailExists = true;
                break;
            }
        }
        if(emailExists){
            throw new DuplicateResourceException("Student","email",request.getEmail());
        }
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGpa(request.getGpa());
        student.setMajor(request.getMajor());
        student.setYear(request.getYear());
        return student;
    }
    public void delete(Long id){
        Student student = findById(id);
        students.remove(student);
    }
    public List<Student> getStudentsByMajor(String major){
        List<Student> res = new ArrayList<>();
        for(var x:students){
            if(x.getMajor().equals(major)){
                res.add(x);
            }
        }
        if(res.isEmpty()){
            throw new ResourceNotFoundException("Student","major",major);
        }
        return res;
    }
    public List<Student> getStudentsByGPA(){
        List<Student> res = new ArrayList<>();
        for(var x:students){
            if(x.getGpa()>=3.6){
                res.add(x);
            }
        }
        if(res.isEmpty()){
            throw new ResourceNotFoundException("Student","GPA",3.6);
        }
        return res;
    }
}
