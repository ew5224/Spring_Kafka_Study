package japbook.jpashop.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded  /// 내장 됐다 사실 Embeddable 이나 Embedded 둘중 하나만 있으면 돼
    private Address address;

    @OneToMany(mappedBy = "member") // mapped by 된거라서 얘는 read 전용이다 . order의 멤버에 따라 변한다
    private List<Order> orders = new ArrayList<>();



}
