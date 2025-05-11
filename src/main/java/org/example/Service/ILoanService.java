package org.example.Service;

import org.example.Model.Loan;
import org.example.Model.LoanReport;

import java.time.LocalDate;
import java.util.List;

public interface ILoanService {
    void registerLoan(Loan loan);
    List<Loan> listenLoan();
    List<Loan> filterLoansByDniStudent(String studentDni);
    List<LoanReport> reportLoan(LocalDate startDate, LocalDate endDate);
}
