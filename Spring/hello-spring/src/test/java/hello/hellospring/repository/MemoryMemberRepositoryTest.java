package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 각 테스트가 종료될 때 마다 실행하는 콜백
    @AfterEach
    public void AfterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("gaudi");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        // 저장한 멤버 객체와 저장소에서 불러온 객체가 같은 객체인지 확인
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        // 테스트 객체 생성
        Member member1 = new Member();
        member1.setName("brook");

        // 저장소에 저장
        memberRepository.save(member1);

        // 저장소에서 이름으로 불러오기
        Member result = memberRepository.findByName(member1.getName()).get();

        // 생성한 객체와 저장소에서 불러온 객체가 같은 객체인지 비교
        Assertions.assertThat(member1).isEqualTo(result);

    }

    @Test
    public void findAll() {
        // 객체 생성 및 저장
        Member member1 = new Member();
        member1.setName("coma");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("tenet");
        memberRepository.save(member2);

        // 저장소에서 불러오기
        List<Member> result = memberRepository.findAll();

        // 사이즈 비교
        Assertions.assertThat(result.size()).isEqualTo(2);
    }

}
