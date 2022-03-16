package jpa.practice2.domain.item;

import jpa.practice2.domain.CategoryItem;
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

    public Item(String name, int price, int stockQuantity, List<CategoryItem> categoryItems) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryItems = categoryItems;
    }
}
