package com.example.btvn3.service;

import com.example.btvn3.dto.request.CreateStudentRequest;
import com.example.btvn3.dto.request.UpdateStudentRequest;
import com.example.btvn3.exception.DuplicateResourceException;
import com.example.btvn3.exception.ResourceNotFoundException;
import com.example.btvn3.model.Student;
import com.example.btvn3.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final List<Student> students = new ArrayList<>();
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional(readOnly = true)
    public List<Student> read(){
        return studentRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", id));
    }

    @Transactional
    public Student create(CreateStudentRequest request){
        boolean studentCodeExists = students.stream().anyMatch(u->u.getStudentCode().equals(request.getStudentCode()));
        boolean emailExists = students.stream().anyMatch(u->u.getEmail().equals(request.getEmail()));
        if(studentCodeExists){
            throw new DuplicateResourceException("Student","student code",request.getStudentCode());
        }if (emailExists) {
            throw new DuplicateResourceException("Student", "email", request.getEmail());
        }
        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGpa(request.getGpa());
        student.setMajor(request.getMajor());
        student.setYear(request.getYear());

        studentRepository.save(student);
        return student;
    }

    @Transactional
    public Student update(UpdateStudentRequest request, Long id){
        Student student = (Student) findById(id);
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
        studentRepository.save(student);
        return student;
    }

    @Transactional
    public void delete(Long id){
        studentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByMajor(String major){
        List<Student> res = studentRepository.findByMajor(major);
        if(res.isEmpty()){
            throw new ResourceNotFoundException("Student","major",major);
        }
        return res;
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByGPA(){
        List<Student> res = studentRepository.findByGpaGreaterThan(3.6);
        if(res.isEmpty()){
            throw new ResourceNotFoundException("Student","GPA",3.6);
        }
        return res;
    }
}
