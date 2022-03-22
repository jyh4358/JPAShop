package jpa.practice2;

import jpa.practice2.domain.*;
import jpa.practice2.domain.item.Book;
import jpa.practice2.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {


    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member1 = createMember("user1", "서울", "노원", "111");
            Member member2 = createMember("user2", "서울", "강북", "222");
            em.persist(member1);
            em.persist(member2);

            Item book1 = createBook("JPA1", 10000, 100, "김영한", "111");
            Item book2 = createBook("JPA2", 20000, 100, "김영한", "222");

            Item book3 = createBook("Spring1", 30000, 200, "몰라요", "333");
            Item book4 = createBook("Spring2", 40000, 300, "몰라요", "444");

            em.persist(book1);
            em.persist(book2);
            em.persist(book3);
            em.persist(book4);


            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            OrderItem orderItem3 = OrderItem.createOrderItem(book3, 30000, 3);
            OrderItem orderItem4 = OrderItem.createOrderItem(book4, 40000, 4);


            Delivery delivery1 = new Delivery(member1.getAddress());
            Order order1 = Order.createOrder(member1, delivery1, orderItem1, orderItem2);
            Delivery delivery2 = new Delivery(member2.getAddress());
            Order order2 = Order.createOrder(member2, delivery2, orderItem3, orderItem4);

            em.persist(order1);
            em.persist(order2);
        }


        private Item createBook(String name, int price, int stockQuantity, String author, String isbn) {
            Item book1 = new Book(name, price, stockQuantity, author, isbn);
            return book1;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member(name, new Address(city, street, zipcode));
            return member;
        }
    }

}
