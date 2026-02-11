package com.example.question2_student_.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private Map<Long, Student> students = new HashMap<>();
    private Long nextId = 6L;

    public StudentController() {
        students.put(1L, new Student(1L, "Humure", "Enock", "humure.enock@email.com", "Computer Science", 3.8));
        students.put(2L, new Student(2L, "Kagame", "Eric", "kagame.eric@email.com", "Computer Science", 3.9));
        students.put(3L, new Student(3L, "Uwase", "Divine", "uwase.divine@email.com", "Mathematics", 3.4));
        students.put(4L, new Student(4L, "Mugisha", "Patrick", "mugisha.patrick@email.com", "Engineering", 3.6));
        students.put(5L, new Student(5L, "Ishimwe", "Grace", "ishimwe.grace@email.com", "Computer Science", 3.2));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Student student = students.get(studentId);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @GetMapping("/major/{major}")
    public List<Student> getStudentsByMajor(@PathVariable String major) {
        return students.values().stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<Student> filterStudentsByGpa(@RequestParam Double gpa) {
        return students.values().stream()
                .filter(s -> s.getGpa() >= gpa)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Student registerStudent(@RequestBody Student student) {
        student.setStudentId(nextId++);
        students.put(student.getStudentId(), student);
        return student;
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        if (!students.containsKey(studentId)) {
            return ResponseEntity.notFound().build();
        }
        updatedStudent.setStudentId(studentId);
        students.put(studentId, updatedStudent);
        return ResponseEntity.ok(updatedStudent);
    }
}
