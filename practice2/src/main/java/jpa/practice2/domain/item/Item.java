package jpa.practice2.domain.item;

import jpa.practice2.domain.CategoryItem;
import jpa.practice2.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Item {

    @Id @GeneratedValue
    @Column
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    public Item(String name, int price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void plusStock(int count) {
        this.stockQuantity += count;
    }

    public void removeStrock(int quantity) {
        int restQuantity = this.stockQuantity - quantity;
        if (restQuantity < 0) {
            throw new NotEnoughStockException("need more stock");
        }
    }
}
