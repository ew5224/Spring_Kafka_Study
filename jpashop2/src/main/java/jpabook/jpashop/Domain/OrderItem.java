package jpabook.jpashop.Domain;


import jpabook.jpashop.Domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="item_id")
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderprice;

    private int count;
    // protected 로 다른 방법으로 생성되는 걸 막는다
    //orderItem orderitem = new ~~ 이런 방식으로 생성하는 것을 막아준다 -- 제약하는 식으로 만드는게 실수를 줄이고 남들의 간섭을 막을 수 있다


    //생성메소드//
    public static OrderItem createOrderItem(Item item, int orderprice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderprice(orderprice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }


    //비즈니스 로직//
    public void cancel(){
        getItem().addStock(count);
    }
    public int getTotalPrice(){
        return getOrderprice() * getCount();
    }


}
