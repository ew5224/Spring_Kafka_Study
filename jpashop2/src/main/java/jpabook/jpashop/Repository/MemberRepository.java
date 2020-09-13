package jpabook.jpashop.Repository;


import jpabook.jpashop.Domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext /// persisten context는 뭘까
    private EntityManager em;

    public void save(Member member){
        em.persist(member);

    }
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findall(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name",name)
                .getResultList();
    }
}
