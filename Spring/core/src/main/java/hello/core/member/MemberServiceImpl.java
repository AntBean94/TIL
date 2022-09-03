package hello.core.member;

public class MemberServiceImpl implements MemberService {

    //    private MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 싱글톤 확인 용도의 테스트코드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
