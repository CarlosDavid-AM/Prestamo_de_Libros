package org.example;

import org.example.Exception.BookNotFoundException;
import org.example.Exception.DuplicateBookException;
import org.example.Exception.DuplicateStudentException;
import org.example.Exception.StudentNotFoundException;
import org.example.Model.Book;
import org.example.Model.Loan;
import org.example.Model.LoanReport;
import org.example.Model.Student;
import org.example.Service.IBookService;
import org.example.Service.ILoanService;
import org.example.Service.IStudentService;
import org.example.Service.impl.BookServiceImpl;
import org.example.Service.impl.LoanServiceImpl;
import org.example.Service.impl.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        boolean exit = false;
        IBookService bookService = new BookServiceImpl();
        IStudentService studentService = new  StudentService();
        ILoanService loanService = new LoanServiceImpl();

        do {
            System.out.println("Menú de Opciones:");
            System.out.println("1. Registrar Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Buscar Libro por ISBN");
            System.out.println("4. Registrar Estudiante");
            System.out.println("5. Listar Estudiantes");
            System.out.println("6. Buscar Estudiante por DNI");
            System.out.println("7. Registrar Préstamo");
            System.out.println("8. Listar Préstamos por Rango de Fecha");
            System.out.println("9. Listar Préstamos por Estudiante");

            System.out.println("0. Salir");

            int choice = getIntInput("Seleccione una opción: ");

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Title: ");
                        String title = reader.readLine();

                        System.out.println("Author: ");
                        String author = reader.readLine();

                        System.out.println("Year: ");
                        int year = Integer.parseInt(reader.readLine());

                        System.out.println("ISBN: ");
                        String isbn = reader.readLine();

                        Book newBook = new Book(title, author, year, isbn);
                        bookService.registerBook(newBook);
                        System.out.println("Book Succeed");
                    } catch (DuplicateBookException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    System.out.println("Listado de libros");
                    bookService.getAllBooks().forEach(System.out::println);

                    break;
                case 3:
                    try {
                        System.out.println("Ingrese el ISBN");
                        String isbn = reader.readLine();

                        Optional<Book> foundBook = bookService.findBookByISBN(isbn);
                        System.out.println(foundBook.get());
                    } catch (BookNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 4:
                    try {
                        System.out.println("Enter student name");
                        String name = reader.readLine();

                        System.out.println("Enter student dni");
                        String dni = reader.readLine();

                        Student studentNew = new Student(name, dni);
                        studentService.registerStudent(studentNew);

                        System.out.println("Student registered successfully!");
                    } catch (DuplicateStudentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 5:
                    System.out.println("Listado de estudiantes");
                    studentService.getAllStudents().forEach(System.out::println);

                    break;
                case 6:
                    try {
                        System.out.println("Enter DNI for find student");
                        String findDni = reader.readLine();
                        Optional<Student> foundStudent = studentService.findBookByDNI(findDni);

                        System.out.println("Student found: " + foundStudent);
                    } catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 7:
                    break;
                case 8:
                    try {
                        System.out.print("Enter start date (yyyy-MM-dd): ");
                        String startDateString = reader.readLine();

                        System.out.print("Enter end date (yyyy-MM-dd): ");
                        String endDateString = reader.readLine();


                        // Convertir las cadenas de fecha a objetos LocalDate y luego llamar al servicio
                        LocalDate startDate = LocalDate.parse(startDateString);
                        LocalDate endDate = LocalDate.parse(endDateString);

                        List<LoanReport> loansByDateRange = loanService.reportLoan(startDate, endDate);
                        System.out.println("List of Loans by Date Range:");
                        loansByDateRange.forEach(System.out::println);
                    } catch (IOException | NumberFormatException ex) {
                        System.out.println("Error en la entrada de datos. Por favor, ingrese datos válidos.");
                    }

                    break;
                case 9:
                    try {
                        System.out.print("Enter student DNI to find loans: ");
                        String studentDni = reader.readLine();


                        // Llamar al servicio para obtener la lista de préstamos por estudiante
                        List<Loan> loansByStudent = loanService.filterLoansByDniStudent(studentDni);
                        System.out.println("List of Loans by Student:");
                        loansByStudent.forEach(System.out::println);
                    }catch (StudentNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (IOException | NumberFormatException ex) {
                        System.out.println("Error en la entrada de datos. Por favor, ingrese datos válidos.");
                    }

                    break;
                case 0:
                    exit = true;
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }

        } while (!exit);
    }

    private static int getIntInput(String message) {
        System.out.print(message);
        while (true) {
            try {
                return Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }
}