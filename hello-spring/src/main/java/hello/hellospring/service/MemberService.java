package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();


    /// 회원가
    public Long join(Member member){
        // 같은 이름이 있는 중복회원은 안된다
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

    }
    /// 컨트롤 T 익스토프 메소
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }


    /** 전체회원조희 **/
    public List<Member> findMembers(){
        return memberRepository.findAll();

    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
