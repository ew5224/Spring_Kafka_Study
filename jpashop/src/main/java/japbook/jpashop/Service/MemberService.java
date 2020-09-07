package japbook.jpashop.Service;


import japbook.jpashop.Domain.Member;
import japbook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional(readOnly = true)//jpa모든 로직은 transaction 안에서 일어나야한다
@RequiredArgsConstructor
public class MemberService {

    //@Autowired  // spring 이 bean 등록 된애를 가져와준다
    private final MemberRepository memberRepository;

    //public MemberService(MemberRepository memberRepository){
      //  this.memberRepository = memberRepository;
    //}

    /// 회원가입
    @Transactional
    public Long join(Member member){
        // 중복회원 검증
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    /// 회원 전체 조회
      /// readOnly를 잡아주면 좀더 최적화된 쿼리로 해준다 붙여ㅈ는게 좋
    public List<Member> findMembers(){
        return memberRepository.findAll();

    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
