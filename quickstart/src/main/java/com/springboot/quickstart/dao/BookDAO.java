package com.springboot.quickstart.dao;

import com.springboot.quickstart.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class BookDAO {
    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"轻量级框架技术",70.0));
        books.add(new Book(1,"java",72.0));
        books.add(new Book(1,"springboot",50.7));
        return books;

    }
}
