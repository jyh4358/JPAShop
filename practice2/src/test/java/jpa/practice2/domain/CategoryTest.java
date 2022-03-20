package jpa.practice2.domain;


import jpa.practice2.domain.item.Book;
import jpa.practice2.domain.item.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class CategoryTest {

    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false)
    @DisplayName("상품 저장 테스트")
    public void categorySaveTest() {
        Category parentCategory = new Category("도서", null);

        em.persist(parentCategory);
        em.flush();
        em.clear();

        Category findCategory = em.find(Category.class, parentCategory.getId());

        Category category2 = new Category("it", findCategory);
        Category category3 = new Category("자기개발", findCategory);


        em.persist(category2);
        em.persist(category3);

        CategoryItem categoryItem1 = new CategoryItem(category2);
        CategoryItem categoryItem2 = new CategoryItem(category3);

        Item item = Book.createBook("JPA", 10000, 10, "김영한", "123", categoryItem1, categoryItem2);

        em.persist(item);






    }

}