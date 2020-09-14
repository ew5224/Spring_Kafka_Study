package jpabook.jpashop.Service;


import jpabook.jpashop.Domain.Delivery;
import jpabook.jpashop.Domain.Member;
import jpabook.jpashop.Domain.Order;
import jpabook.jpashop.Domain.OrderItem;
import jpabook.jpashop.Domain.item.Item;
import jpabook.jpashop.Repository.ItemRepository;
import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.Repository.OrderRepository;
import jpabook.jpashop.Repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    // 도메인 모델 패턴 - 엔티티에서 대부분의 비즈니스 로직을 가지고 있는 것 - 객체지향적
    // 트랜잭션 스크립트 패턴 - 트랜잭션에서 다 처리하는 방식으로함
    // 문맥에 따라서 장단이 있으니까 알아서 쓰길, 한 프로젝트 안에서도 공존할 수 있음.
    @Transactional
    public Long order(Long memberId, Long itemId, int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성  // cascade는 하나의 다른 엔티티와 연결되어있을때만 쓰는 것이 안전하다. 모르겠으면 쓰지마라
        OrderItem orderitem = OrderItem.createOrderItem(item, item.getPrice(), count);


        //주문
        Order order = Order.createOrder(member, delivery, orderitem);

        //주문 저장  // cascade가 있기 떄문에 orderitem 과 딜러비러가 자동 저
        orderRepository.save(order);
        return order.getId();
    }

    // 취소
    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문취소
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }

    //검색


}
