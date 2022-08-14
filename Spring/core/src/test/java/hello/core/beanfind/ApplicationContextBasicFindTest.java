package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 스프링 빈 조회 - 기본
    // 가장 기본적인 조회 방법
    // ac.getBean(빈 이름, 타입), ac.getBean(타입) => 조회 대상 스프링빈이 없으면 예외 발생

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        assertThat(orderService).isInstanceOf(OrderService.class);
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType() {
        MemberService bean = ac.getBean(MemberService.class);

        assertThat(bean).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        assertThat(orderService).isInstanceOf(OrderServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxx", MemberService.class));
    }


}
