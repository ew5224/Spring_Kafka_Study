package jpabook.jpashop.Domain.item;


import jpabook.jpashop.Domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items", fetch =  FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 로직 추가 - 엔테티가 스스로 해결할수 있는거는 엔티티 안에 넣어주는게 좋다.. 객체지향이다.. 1재고 늘줄//
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }
    /// setter로 밖에서 계산해서 가져오는게 아니라 안에서 알아서 계산하게 하는게 객체지향적이다

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if( restStock <0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
