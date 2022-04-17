
// Dog, Cat 다중 상속이 가능함을 알 수 있다.
public class Main implements Dog, Cat {
    // 인터페이스(Interface)
    /**
     * 인터페이스(Interface): 설계의 본질
     * 인터페이스(interface)는 얼핏 보기에는 추상(Abstract) 클래스와 매우 흡사한 개념으로
     * 느껴질 수 있다. 인터페이스는 숙련된 자바 개발자들에게 매우 선호되는 설계 기능이면서
     * 자바에서 다중 상속을 구현하게 해주는 고급 기술이기도 하다. 추상 클래스는 추상 메소드 외에
     * 멤버 변수나 일반 메소드를 가질 수 있지만 인터페이스에서는 반드시 사전에 정의된 추상
     * 메소드와 상수만을 가질 수 있다는 특징이 있다.
     * 인터페이스는 팀 프로젝트의 동시 작업에 유리하고 일반적으로 추상보다 요구되는 설계의 기준이
     * 높아서 더 체계적이라는 평을 받는다.
     */

    public static void main(String[] args) {

        Main main = new Main();
        main.crying();
        main.show();
    }

    @Override
    public void crying() {
        System.out.println("월! 월! 월!");
    }

    @Override
    public void show() {
        System.out.println("Hello World!");
    }

    @Override
    public void one() {
        System.out.println("one");
    }

    @Override
    public void two() {
        System.out.println("two");
    }
}
