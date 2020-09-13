package jpabook.jpashop.Repository;


import jpabook.jpashop.Domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save (Item item){
        if(item.getId()==null){ /// id가 없다는 건 완전 새로 넣는거다 --> id 가 없으면 새로 넣으면 된다
            em.persist(item);
        }else{ // id가 있어?? 그럼 update처럼 하자
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class ,id);
    }
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }
}
