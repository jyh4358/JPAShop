package jpa.practice2.service;

import jpa.practice2.domain.Delivery;
import jpa.practice2.domain.Member;
import jpa.practice2.domain.Order;
import jpa.practice2.domain.OrderItem;
import jpa.practice2.domain.item.Item;
import jpa.practice2.repository.ItemRepository;
import jpa.practice2.repository.MemberRepository;
import jpa.practice2.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        Member member = memberRepository.findMember(memberId);
        Item item = itemRepository.findById(itemId);

        Delivery delivery = new Delivery(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);


        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {

        //  주문 조회
        Order order = orderRepository.findOne(orderId);
        //  주문 취소
        order.cancel();
    }




}
