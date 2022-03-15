package jpa.practice.domain.item;

import jpa.practice.domain.CategoryItem;
import lombok.Getter;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
public class Book extends Item{
    private String author;
    private String isbn;

    public Book() {
    }

    public Book(String name, int price, int stockQuantity, String author, String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
