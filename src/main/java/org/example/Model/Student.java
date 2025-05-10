package org.example.Model;

public class Student {
    private String name;
    private int dni;

    public Student(String name, int dni) {
        this.name = name;
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public int getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", dni=" + dni +
                '}';
    }
}
