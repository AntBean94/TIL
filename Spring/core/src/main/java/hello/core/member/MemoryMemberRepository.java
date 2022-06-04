package hello.core.member;

import java.util.HashMap;

public class MemoryMemberRepository implements MemberRepository {

    // 저장소 역할을 할 메모리
    // 동시성 이슈로인해 concurrent hashmap을 사용해야하지만 예제이므로 생략
    private static HashMap<Long, Member> store = new HashMap<>();
    private static Long num = 1L;

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
