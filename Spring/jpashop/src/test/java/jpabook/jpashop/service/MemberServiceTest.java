package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)    // 스프링과 테스트 통합
@SpringBootTest                 // 스프링 띄우고 테스트(안하면 다 실패)
@Transactional
public class MemberServiceTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;
    @Autowired private EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("gwak");

        // when
        Long id = memberService.join(member);
        // 쿼리를 보고 싶다면 ..
        em.flush();

        // then
        assertEquals(member, memberRepository.findOne(id));
    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("gaudi");

        Member member2 = new Member();
        member2.setName("gaudi");

        // when
        memberService.join(member1);
        memberService.join(member2);    // 예외가 발생해야 한다.
        
        // then
        fail("예외가 발생해야 한다.");
    }

}