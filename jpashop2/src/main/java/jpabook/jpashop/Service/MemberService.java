package jpabook.jpashop.Service;

import jpabook.jpashop.Domain.Member;
import jpabook.jpashop.Repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional/// jpa의 로직은 transcation 안에서 실행이 되어야한다
/// @AllArgsConstructor   @RequiredArgsConstructor 파이널 붙은애들만 생성자만 만들어준다 밑에 autowire 없어져도
public class MemberService {


    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }  // 생성자가 하나만 있는 경우 여기에 autowired가 없어도 injection을 해준다

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member); /// 같은 이름의 회원을 체
        return member.getId();
    }


    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }

    }
    @Transactional(readOnly = true)  ///조회에는 가급적으로 readonly 를 넣어주
    public List<Member> findMembers(){
        return memberRepository.findall();

    }
    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
