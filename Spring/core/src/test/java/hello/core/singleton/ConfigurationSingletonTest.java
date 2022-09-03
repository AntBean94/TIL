package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("싱글톤 테스트")
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // memberRepository가 같은 인스턴스인지 확인하기 위해 불러온다.
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        // 비교
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        // 같은 인스턴스를 참조하고 있다.
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // AppConfig도 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);

        // bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$a6b188b8 출력
        System.out.println("bean = " + bean.getClass());

        // AppConfig가 아니라 CGLIB가 붙은 복잡한 클래스가 출력되는것을 확인할 수 있다.
        // @Configuration 어노테이션이 붙으면 바이트코드를 조작해 AppConfig를 상속하여 싱글톤을 보장하는 클래스를 만든다.
        // @Configuration 어노테이션이 없으면 memberRepository도 각각 다른 인스턴스가 출력된다.

    }
}
