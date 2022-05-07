package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     *
     * 일반적으로 서비스는 비즈니스 의존적으로 네이밍을 하며
     * 레포지토리는 비즈니스와 독립적인 일반적인 네이밍을 한다.
     */
    public Long join(Member member) {
        // 이름 중복 체크 기능을 추가한다면?
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {     // ifPresent: null이 아닌경우
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

        // 위의 코드를 개선
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

        // 위의 코드를 한번 더 개선(분리)
        validateDuplicateMember(member);

        // 저장 및 id 반환
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
