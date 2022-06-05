package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 순수한 java코드로만 작성한 로직 + 테스트
// 한계가 명확하므로 junit을 사용한 테스트를 진행해본다.
public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "Gaudi", Grade.VIP);
        memberService.join(member);

        // 저장한 멤버와 member 변수에 담긴 멤버가 같은 멤버인지 체크
        Member findMember = memberService.findMember(member.getId());
        System.out.println("member: " + member.getName());
        System.out.println("findMember: " + findMember.getName());

    }
}
