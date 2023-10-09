package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @Autowired
    MemberRepository memberRepository;

    // tdd template 추가
    @Test
    @Transactional
    public void testMember() {
        // given
        Member member = new Member();
        member.setName("memberA");

        // when
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.find(savedId);

        // then
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
//        Assertions.assertThat(findMember).isEqualTo(member);
    }


}