package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/* 커맨드 시프트 T 로 쉽게 테스트 만들수 있다 */

class MemberServiceTest {
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        memberRepository.clearstore();
    }


    @Test
    void join() {
       //given
        Member member = new Member();
        member.setName("spring");

       //when
       Long saveId = memberService.join(member);

       //then
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));


        /*try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
        */
        //then




    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}