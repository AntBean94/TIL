package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// 실무에서는 Setter 어노테이션을 사용하기보다 변경지점이 명확하도록 별도의 비즈니스 메서드를 작성해서 사용하자
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    /** 엔티티 설계시 주의사항)
     * 1. 컬렉션은 필드에서 초기화 하는 것이 안전하다.
     * - null 문제로부터 안전하다.
     * - 하이버네이트는 엔티티를 영속화 할 때, 컬렉션을 감싸서 하이버네이트가 제공하는
     *   내장 컬렉션으로 변경한다.
     *   만약 임의의 메서드에서 컬렉션을 생성하면 하이버네이트 내부 매커니즘에 문제가
     *   발생할 수 있다. 따라서 필드레벨에서 생성하는것이 안전하고 코드도 간결하다.
     */

}
