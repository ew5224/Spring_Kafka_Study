package jpabook.jpashop.Repository;


import jpabook.jpashop.Domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    //조회는 동적쿼리를 써야해서 일단 쫌 따 함!!//

    public List<Order> findAll(OrderSearch orderSearch){  // JPA Criteria로 작성해보자! 응 안할께 근데 이것도 실무에서 쓰라고 만든게 아닌 거 같

        /*
        return em.createQuery("select o from Order o join o.member m"+
                " where o.status = :status" +   /// 만약세 상태랑 name이 입력이 안되어있으면?
                " and m.name like :name", Order.class)
                .setParameter("status",orderSearch.getOrderStatus())
                .setParameter("name",orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList(); */
/// Query DSL 로 만들꺼야
        // 아직 안했음
    }

}
