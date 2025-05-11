package org.example.Service;

import org.example.Model.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    void registerStudent(Student student);
    List<Student> getAllStudents();
    Optional<Student> findBookByDNI(String dni);
}
