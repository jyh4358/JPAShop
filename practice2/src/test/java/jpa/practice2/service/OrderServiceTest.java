package jpa.practice2.service;

import jpa.practice2.domain.*;
import jpa.practice2.domain.item.Book;
import jpa.practice2.domain.item.Item;
import jpa.practice2.exception.NotEnoughStockException;
import jpa.practice2.repository.MemberRepository;
import jpa.practice2.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;


    @Test
    @DisplayName("상품주문")
    public void orderTest() {

        Member member1 = new Member("member1", new Address("서울시", "노원구", "111-111"));
        em.persist(member1);

        Category parentCategory = new Category("도서", null);
        em.persist(parentCategory);

        Category findCategory = em.find(Category.class, parentCategory.getId());

        Category category1 = new Category("it", findCategory);
        Category category2 = new Category("자기개발", findCategory);

        em.persist(category1);
        em.persist(category2);

        CategoryItem categoryItem1 = new CategoryItem(category1);
        CategoryItem categoryItem2 = new CategoryItem(category2);

        Item item = Book.createBook("JPA", 10000, 100, "김영한", "123", categoryItem1, categoryItem2);
        em.persist(item);

        Long orderId = orderService.order(member1.getId(), item.getId(), 10);

        Order findOrder = orderRepository.findOne(orderId);

        Assertions.assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(findOrder.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(findOrder.getTotalPrice()).isEqualTo(item.getPrice() * 10);
        Assertions.assertThat(item.getStockQuantity()).isEqualTo(90);
    }

    @Test
    @DisplayName("상품재고 주문 초과")
    public void orderOver() {
        Member member1 = new Member("member1", new Address("서울시", "노원구", "111-111"));
        em.persist(member1);

        Category parentCategory = new Category("도서", null);
        em.persist(parentCategory);

        Category findCategory = em.find(Category.class, parentCategory.getId());

        Category category1 = new Category("it", findCategory);
        Category category2 = new Category("자기개발", findCategory);

        em.persist(category1);
        em.persist(category2);

        CategoryItem categoryItem1 = new CategoryItem(category1);
        CategoryItem categoryItem2 = new CategoryItem(category2);

        Item item = Book.createBook("JPA", 10000, 100, "김영한", "123", categoryItem1, categoryItem2);
        em.persist(item);

        assertThrows(NotEnoughStockException.class, () ->{
            orderService.order(member1.getId(), item.getId(), 100);
        });
    }
}