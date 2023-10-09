package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")     // sql 문법중에 order by라는 문법이 있으므로 관례적으로 s를 붙여 복수형으로 사용
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;  // 주문회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // cacade: 영속성 전이, 저장이나 삭제등 특정 메서드가 실행될 때 연관관계 엔티티에도 해당 상태를 전이할 것인지 설정할 수 있음
    // 라이브사이클이 동일하거나 부모 엔티티에서만 함께 사용되는 경우 1:N 관계에서 1에 설정하면 된다.
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;      // 배송정보

    private LocalDateTime orderDate;    // 주문시간

    @Enumerated(EnumType.STRING)
    // defualt값이 ORDINAL이기 때문에 STRING값으로 바꿔준 것
    private OrderStatus status;     // 주문상태 [ORDER, CANCEL]

    /** 엔티티 설계시 주의할 점)
     * 2. 모든 연관관계는 지연로딩(LAZY)로 설정
     * - 즉시(EAGER)로딩은 예측이 어렵고, 어떤 SQL이 실행될지 추적하기 어렵다. 특히 JPQL을 실행할 때
     *   N + 1 문제가 자주 발생한다.
     * - 실무에서 모든 연관관계는 지연로딩(LAZY)으로 설정한다.
     * - 연관된 엔티티를 조회하고 싶다면 fetch join기능을 사용하거나 엔티티 그래프 기능을 사용한다.
     * - XToOne 관계는 즉시로딩이 default 셋팅이므로 지연로딩으로 설정값을 바꿔줘야한다.
     */

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //==생성 메서드==//
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    //==비즈니스 로직==//
    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
