package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;  // 주문회원

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;      // 배송정보

    private LocalDateTime orderDate;    // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status;     // 주문상태 [ORDER, CANCEL]

    /** 엔티티 설계시 주의할 점)
     * 2. 모든 연관관계는 지연로딩(LAZY)로 설정
     * - 즉시(EAGER)로딩은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어렵다. 특히 JPQL을 실행할 때
     *   N + 1 문제가 자주 발생한다.
     * - 실무에서 모든 연관관계는 지연로딩(LAZY)으로 설정한다.
     * - 연관된 엔티티를 조회하고 싶다면 fetch join기능을 사용하거나 엔티티 그래프 기능을 사용한다.
     * - XToOne 관계는 즉시로딩이 default 셋팅이므로 지연로딩으로 설정값을 바꿔줘야한다.
     */
}
