package org.example.Service.impl;

import org.example.Exception.DuplicateLoanException;
import org.example.Exception.ErrorMessage;
import org.example.Model.Loan;
import org.example.Model.LoanReport;
import org.example.Service.ILoanService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanServiceImpl implements ILoanService {
    private final List<Loan> loans = new ArrayList<>();

    @Override
    public void registerLoan(Loan loan) {
        boolean isDuplicateLoan = loans.stream()
                        .anyMatch(existingLoan -> existingLoan.getBook().equals(loan.getBook())
                        && existingLoan.getStudent().equals(loan.getStudent()));

        if (isDuplicateLoan) {
            throw new DuplicateLoanException(ErrorMessage.DUPLICATE_LOAN.formatMessage(
                    loan.getBook().getTitle(), loan.getStudent().getName()
            ));
        }

        loans.add(loan);
    }

    @Override
    public List<Loan> listenLoan() {
        return loans;
    }

    @Override
    public List<Loan> filterLoansByDniStudent(String studentDni) {
        return loans.stream()
                .filter(loan -> loan.getStudent().getDni().equals(studentDni))
                .toList();
    }

    @Override
    public List<LoanReport> reportLoan(LocalDate startDate, LocalDate endDate) {
        return loans.stream()
                .filter(
                        loan -> (loan.getLoanDate().isAfter(startDate) || loan.getLoanDate().isBefore(startDate))
                        && loan.getLoanDate().isAfter(endDate) || loan.getLoanDate().isBefore(endDate)
                ).map(
                        loan -> new LoanReport(
                                loan.getBook().getTitle(),
                                loan.getLoanDate(),
                                loan.getReturnDate(),
                                loan.getStudent().getName()
                        )
                ).toList();
    }
}
