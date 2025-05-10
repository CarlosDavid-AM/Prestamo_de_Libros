package org.example.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Loan {
    private String id;
    private Book book;
    private Student student;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean isLoan;

    public Loan(Book book, Student student, boolean isLoan) {
        this.id = UUID.randomUUID().toString();
        this.book = book;
        this.student = student;
        this.isLoan = isLoan;
        this.loanDate = LocalDate.now();
        this.returnDate = loanDate.plusDays(7);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isLoan() {
        return isLoan;
    }

    public void setLoan(boolean loan) {
        isLoan = loan;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id='" + id + '\'' +
                ", book=" + book +
                ", student=" + student +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                ", isLoan=" + isLoan +
                '}';
    }
}
