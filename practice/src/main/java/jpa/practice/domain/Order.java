package jpa.practice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Order(Member member, Delivery delivery, LocalDateTime orderDate, OrderStatus status, List<OrderItem> orderItems) {
        this.member = member;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
        this.orderItems = orderItems;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

}
