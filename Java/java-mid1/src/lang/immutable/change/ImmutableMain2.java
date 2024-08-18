package lang.immutable.change;

public class ImmutableMain2 {
    public static void main(String[] args) {
        ImmutableObj obj1 = new ImmutableObj(10);
        obj1.add(20);

        System.out.println("obj1 = " + obj1.getValue());
        // 계산 이후 값을 사용하지 않으면 기존값에는 변경이 없기 때문에 실행결과 아무것도 처리되지 않은 것 처럼 보인다.
    }
}
