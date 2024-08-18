package lang.immutable.address;

public class RefMain1_3 {
    public static void main(String[] args) {
        Address a = new Address("서울");
        Address b = a;
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        change(b, "부산"); // 구체적인 동작이 숨어있는경우 버그 예측이 어려울 수 있다.
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    private static void change(Address address, String newValue) {
        System.out.println("주소 값을 변경합니다. -> " + newValue);
        address.setValue(newValue);
    }
}
