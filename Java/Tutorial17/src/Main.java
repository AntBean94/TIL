public class Main {
    // 최종(Final)
    /**
     * 최종(Final): 최종적으로 규정하다.
     * 자바에서 절대 변하지 않는 특정한 것을 정하고 싶을 때는 최종(Final)을 사용한다.
     * 이 키워드는 변수, 메소드, 클래스에 모두 사용할 수 있다.
     * 변수에 사용할 경우 변하지 않는 상수가 된다.
     * 메소드에 사용할 때는 재정의가 불가능한 메소드가 되며,
     * 클래스에 사용할 경우 상속이 불가능한 하나의 완전한 클래스가 된다.
     */

    public static void main(String[] args) {

        final int number = 10;
//        number = 5;    => 에러가 발생한다.
        System.out.println(number);

        Children children = new Children();
        children.test();
    }
}
