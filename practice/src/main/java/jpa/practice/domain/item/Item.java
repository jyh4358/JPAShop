package jpa.practice.domain.item;

import jpa.practice.domain.CategoryItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@NoArgsConstructor(access = PROTECTED)
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
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
