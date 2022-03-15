package jpa.practice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "Order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(Member member, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
        this.member = member;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order(member, delivery, LocalDateTime.now(), OrderStatus.ORDER);
        for (OrderItem orderItem : orderItems) {
            order.orderItems.add(orderItem);
        }
        return order;
    }
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }
        status = OrderStatus.CANCEL;
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
    //==조회 로직==//
    /** 전체 주문 가격 조회 */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
