package lang.object.equals;

import java.util.Objects;

public class UserV2 {

    private String id;

    public UserV2(String id) {
        this.id = id;
    }

//    @Override
//    public boolean equals(Object obj) {
//        UserV2 user = (UserV2) obj;
//        return id.equals(user.id);
//    }

    // 정확한 equals구현(커맨드 + N,으로 자동생성)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserV2 userV2 = (UserV2) o;
        return Objects.equals(id, userV2.id);
    }

    /**
     * equals() 메서드를 구현할 때 지켜야 하는 규칙
     * 1. 반사성: 객체는 자기 자신과 동등해야한다.
     * 2. 대칭성: 두 객체가 서로에 대해 동일하다고 판단하면, 이는 양방향으로 동일해야한다.
     * 3. 추이성: 만약 한 객체가 두 번째 객체와 동일하고, 두 번째 객체가 세 번째 객체와 동일하다면,
     *  첫 번째 객체는 세 번째 객체와도 동일해야 한다.
     * 4. 이로간성: 두 객체의 상태가 변경되지 않는 한, equals() 메소드는 항상 동일한 값을 반환해야 한다.
     * 5. null에 대한 비교: 모든 객체는 null과 비교했을 때 false를 반환해야 한다.
     */

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
