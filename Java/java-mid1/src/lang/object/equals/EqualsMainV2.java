package lang.object.equals;

public class EqualsMainV2 {
    public static void main(String[] args) {
        UserV2 user1 = new UserV2("id-100");
        UserV2 user2 = new UserV2("id-100");

        System.out.println("identity = " + (user1 == user2));
        System.out.println("equality = " + (user1.equals(user2)));

        // 동일성은 참조값이 같은지(물리적으로 같은 메모리를 바라보고 있는지) 확인한다.
        // 동등성은 다양한 논리적 기준에 따라 같음을 확인한다.(논리적으로 같은 값인지 확인)
    }
}
