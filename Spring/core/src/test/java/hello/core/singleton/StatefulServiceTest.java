package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // Thread A: 사용자A 10000원 주문
        statefulService1.order("gaudi", 10000);
        // Thread B: 사용자B 20000원 주문
        statefulService2.order("dong", 20000);

        // Thread A: 사용자A 주문금액 조회 => 기대금액(10000)과 다른 금액 출력
        System.out.println(statefulService1.getPrice());
        // Thread B: 사용자B 주문금액 조회
        System.out.println(statefulService2.getPrice());

        assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
