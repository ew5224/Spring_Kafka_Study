package jpabook.jpashop.Repository;

import jpabook.jpashop.Domain.Order;
import jpabook.jpashop.Domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter @Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus; // 주문상태 [Order. CANCEL]

}
