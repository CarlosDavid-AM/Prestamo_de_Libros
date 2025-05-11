package org.example.Service.impl;

import org.example.Exception.BookNotFoundException;
import org.example.Exception.DuplicateBookException;
import org.example.Exception.ErrorMessage;
import org.example.Model.Book;
import org.example.Service.IBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements IBookService {
    private final List<Book> books = new ArrayList<>();

    @Override
    public void registerBook(Book book) {
        boolean isDuplicateIsbn = books.stream()
                .anyMatch(existingBook -> existingBook.getIsbn().equals(book.getIsbn()));

        if(isDuplicateIsbn) {
            throw new DuplicateBookException(ErrorMessage.DUPLICATE_BOOK.formatMessage(book.getTitle(), book.getIsbn()));
        }

        books.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Optional<Book> findBookByISBN(String isbn) {
        Optional<Book> optionalBook = books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();

        if (optionalBook.isEmpty()) {
            throw new BookNotFoundException(
                    ErrorMessage.BOOK_NOT_FOUND.formatMessage(isbn)
            );
        }

        return optionalBook;
    }
}
