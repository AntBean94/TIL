package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    => DIP 위반(의존관계 역전 원칙, 구현 클래스에 의존하고 있다.)


//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;  // 구현 클래스는 어디에? => 누군가가 orderServiceImpl에 대시 주입
//    생성자가 1개인 경우 autowired를 생략해도 자동주입(스프링 빈에만 해당)
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    // 의존관계 자동 주입

    // 1. 생성자 주입
    // - 생성자 호출 시점에 딱 1번만 호출되는것이 보장된다.
    // - 불변, 필수 의존관계에 사용
    // - 생성자가 1개인경우 Autowired 생략 가능
//    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy;
//
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    // 2. 수정자 주입
    // - setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법이다.
    // - 선택, 변경 가능성이 있는 의존관계에서 사용
    // - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법
    // - 컴파일 단계에서 오류를 반환하지 않기때문에 나중에 nullPointException이 발생할 수 있다.(따라서, 필수의존관계라면 생성자주입을 선택한다.)
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

    // Autowired 어노테이션은 주입할 자바빈이 없으면 오류를 반환한다.
    // 오류를 반환하지 않게 하려면 (required = false) 옵션을 추가한다.
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }


    // 3. 필드 주입
    // - 코드가 간결하지만 외부에서 변경이 불가능해서 테스트하기 힘들다.
    // - DI 프레임워크가 없으면 아무것도 할 수 없다.(사용하지 않는것을 권장)
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private DiscountPolicy discountPolicy;


    // 4. 일반 메서드 주입
    // - 한번에 여러 필드를 주입할 수 있다.
    // - 일반적으로 잘 사용하지 않음
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }


    // 최신 트렌드 (lombok - requiredArgsConstructor 사용, final 키워드가 붙어있으면 생성자를 자동으로 추가해준다.)
    private final MemberRepository memberRepository;
    private final @MainDiscountPolicy DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤 확인 용도의 테스트코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
