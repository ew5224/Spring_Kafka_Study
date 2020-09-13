package jpabook.jpashop.Service;

import jpabook.jpashop.Domain.Member;
import jpabook.jpashop.Repository.MemberRepository;
import org.graalvm.compiler.debug.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Kim");

        //when
        Long saveId = memberService.join(member);
        //then
        //em.flush();  /// flush 강제로 db에 쿼리 날림
        // 같은 transactional 안에서 id가 같으면 같은 애로 취급한다
       assertEquals(member, memberRepository.findOne(saveId));



    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("Kim1");

        Member member2 = new Member();
        member2.setName("Kim1");
        //when
        memberService.join(member1);
        memberService.join(member2);  // 예외가 발생한다 // try catch를 안하더라도 illegalState Exception이 뜨면 잡아준다

           //then
        fail("예외가 발생해야 한다.");// 코드가 여기까지 오면 안된
    }

}