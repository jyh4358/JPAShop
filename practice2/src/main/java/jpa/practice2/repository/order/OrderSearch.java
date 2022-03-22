package jpa.practice2.repository.order;

import jpa.practice2.domain.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;

    public OrderSearch(String memberName, OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderStatus = orderStatus;
    }
}
