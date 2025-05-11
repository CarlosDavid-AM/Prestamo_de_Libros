package org.example.Service;

import org.example.Model.Loan;

import java.util.List;

public interface ILoanService {
    void registerLoan(Loan loan);
    List<Loan> filterLoansByDniStudent(String studentDni);
}
