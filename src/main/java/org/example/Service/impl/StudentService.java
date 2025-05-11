package org.example.Service.impl;

import org.example.Exception.DuplicateStudentException;
import org.example.Exception.ErrorMessage;
import org.example.Exception.StudentNotFoundException;
import org.example.Model.Book;
import org.example.Model.Student;
import org.example.Service.IStudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService implements IStudentService {
    List<Student> students = new ArrayList<>();

    @Override
    public void registerStudent(Student student) {
        boolean isDuplicateStudent = students.stream()
                .anyMatch(existingStudent -> existingStudent.getDni().equals(student.getDni())
                    && existingStudent.getName().equals(student.getName()));

        if (isDuplicateStudent) {
            throw new DuplicateStudentException(
                    ErrorMessage.DUPLICATE_STUDENT.formatMessage(student.getDni(), student.getName())
            );
        }

        students.add(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Optional<Student> findBookByDNI(String dni) {
        Optional<Student> optionalStudent = students.stream()
                .filter(student -> student.getDni().equals(dni))
                .findFirst();

        if (optionalStudent.isEmpty()) {
            throw new StudentNotFoundException(
                    ErrorMessage.STUDENT_NOT_FOUND.formatMessage(dni)
            );
        }

        return optionalStudent;
    }
}
