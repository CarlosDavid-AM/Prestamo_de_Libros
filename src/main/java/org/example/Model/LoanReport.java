package org.example.Model;

import java.time.LocalDate;

public record LoanReport(
        String title,
        LocalDate loanDate,
        LocalDate returnLoan,
        String studentName
) {
}
