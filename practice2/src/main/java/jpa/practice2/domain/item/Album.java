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
public class Album extends Item{

    private String artist;
    private String etc;

    public Album(String name, int price, int stockQuantity, String artist, String etc) {
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

    public static Item createAlbum(String name, int price, int stockQuantity, String artist, String etc, CategoryItem... categoryItems) {
        Item album = new Album(name, price, stockQuantity, artist, etc);
        for (CategoryItem categoryItem : categoryItems) {
            album.addCategoryItem(categoryItem);
        }
        return album;
    }
}
