package jpa.practice2.domain;


import jpa.practice2.domain.item.Album;
import jpa.practice2.domain.item.Book;
import jpa.practice2.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
class ItemTest {

    @Autowired
    EntityManager em;

    @Test
    @Rollback(value = false)
    @DisplayName("상품 저장 테스트")
    public void itemSaveTest() {
        Item book = new Book("JPA", 10000, 10, "김영한", "123");

        em.persist(book);

        em.flush();
        em.clear();

        Item item = em.find(Item.class, book.getId());

        Assertions.assertThat(item.getName()).isEqualTo(book.getName());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("상품 저장 테스트")
    public void itemFindTest() {
        Item book = new Book("JPA", 10000, 10, "김영한", "123");
        Item album = new Album("베토벤", 20000, 20, "베토벤", "교향곡");

        em.persist(book);
        em.persist(album);

        em.flush();
        em.clear();
        List<Item> resultList = em.createQuery(
                        "select i from Item i where dtype = :type", Item.class
                )
                .setParameter("type", "Book")
                .getResultList();

    }



}