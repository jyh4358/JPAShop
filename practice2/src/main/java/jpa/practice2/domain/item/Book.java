package jpa.practice2.domain.item;

import jpa.practice2.domain.CategoryItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item{

    private String author;
    private String isbn;

    public Book(String name, int price, int stockQuantity, String author, String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
    public static Item createBook(String name, int price, int stockQuantity, String author, String isbn, CategoryItem... categoryItems) {
        Item book = new Book(name, price, stockQuantity, author, isbn);
        for (CategoryItem categoryItem : categoryItems) {
            book.addCategoryItem(categoryItem);
        }
        return book;
    }
}
