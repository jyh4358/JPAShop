package jpa.practice.service;

import jpa.practice.domain.item.Book;
import jpa.practice.domain.item.Item;
import jpa.practice.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {
    
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    @Test
    @DisplayName("상품 등록 테스트")
    public void saveItem() {
        Item book = new Book("jpa", 10000, 10, "김영한", "123");
        itemService.saveItem(book);

        Assertions.assertThat(itemService.findOne(book.getId())).isEqualTo(book);
    }

    @Test
    @DisplayName("상품 목록 테스트")
    public void listItem() {
        Item book1 = new Book("jpa1", 10000, 10, "김영한", "123");
        Item book2 = new Book("jpa2", 10000, 10, "김영한", "123");
        itemService.saveItem(book1);
        itemService.saveItem(book2);

        Assertions.assertThat(itemService.findItems().size()).isEqualTo(2);
    }

}