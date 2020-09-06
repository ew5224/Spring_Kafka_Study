package Spring_Kafka_Project.Linked_Schedule.Repository;

import Spring_Kafka_Project.Linked_Schedule.Domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface Repostiory {

        Member save(Member member);
        Optional<Member> findById(Long id);
        Optional<Member> findByName(String name);
        List<Member> findAll();

}
