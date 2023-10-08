package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {};

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    /** 참고) 값 타입은 변경 불가능하게 설계해야한다.
     * @Setter 어노테이션을 제거하고, 생성자에서 값을 모두 초기화해서 변경불가능하게 만든다.
     * JPA스펙상 엔티티나 임베디드 타입은 자바 기본 생성자를 public, protected 로 설정해야한다.
     * public 보다는 protected로 설정하는것이 비교적 안전하므로 protected로 설정한다.
     *
     * JPA가 이런 규약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플렉션과 같은 기술을
     * 사용할 수 있도록 지원해야하기 때문이다.
     */
}
