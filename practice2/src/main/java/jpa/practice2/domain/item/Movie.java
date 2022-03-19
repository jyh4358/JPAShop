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
public class Movie extends Item{

    private String director;
    private String actor;

    public Movie(String name, int price, int stockQuantity, String director, String actor) {
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }

    public static Item createMovie(String name, int price, int stockQuantity, String director, String actor, CategoryItem... categoryItems) {
        Item movie = new Movie(name, price, stockQuantity, director, actor);
        for (CategoryItem categoryItem : categoryItems) {
            movie.addCategoryItem(categoryItem);
        }
        return movie;
    }
}
