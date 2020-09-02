package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();


    /// test 하나 돌때마다 실행
    /// 테스트를 먼저 만들고 구현하는걸 테스트주도개발 TDD라고 한다.
    @AfterEach
    public void afterEach(){
        repository.clearstore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        /// Optional을 받아오니까 .get으로 일단 불러와봄
        Member result =repository.findById(member.getId()).get();
        /// 계속 프린트해서 볼수 없으니까
        /// member 와 result를 비교해서 같으면 초록불 다르면 엑스
        ///Assertions.assertEquals(member, null);
        Assertions.assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        /// shift f6
        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);

    }


    //test 는 순서가 따로 없다 모른다 findall이 먼저 돌아갔네
    /// 따라서 테스트가 끝나면 데이터clear해줘야한
    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result =repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
