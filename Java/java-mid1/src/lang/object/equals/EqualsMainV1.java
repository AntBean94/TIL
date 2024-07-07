package lang.object.equals;

public class EqualsMainV1 {
    public static void main(String[] args) {
        UserV1 user1 = new UserV1("id-100");
        UserV1 user2 = new UserV1("id-100");

        System.out.println("identity = " + (user1 == user2));
        System.out.println("equality = " + (user1.equals(user2)));
        // Objects가 기본적으로 제공하는 equals는 ==을 제공한다.
        // 따라서 overriding을 통해 용도에 맞게 변경한다.

        // 동등성이라는 개념은 각각의 클래스마다 다르다. 어떤 클래스는 주민등록번호를, 어떤 클래스는
        // 고객의 연락처를 기반으로 동등성을 처리할 수 있다.
        // 따라서 동등성 비교를 위해 equals()를 재정의해서 사용한다.
    }
}
