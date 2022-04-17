import java.util.Scanner;

public class Main {
    // 다형성(Polymorphism)
    /**
     * 다형성은 기본적으로 다양한 형태의 성질을 가진다는 의미를 가지고 있다.
     * 기본적으로 자바는 다형성을 그 특징으로 가지는 객체 지향 프로그래밍 언어이며,
     * 자바에서는 이 다형성을 이용하여 객체를 사용할 때 사용하는 변수 형태를 바꾸어
     * 여러 타입의 객체를 참조할 수 있다.
     * 결과적으로 이러한 다형성의 개념을 적절하게 이용할 때 프로그램의 소스 코드를 유연하게
     * 구성할 수 있다.
     *
     * 다형성은 부모 클래스 타입의 참조 변수로 하위 클래스의 객체를 참조할 수 있게 해준다.
     */

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("딸기: 1, 바나나: 2");
        int input = sc.nextInt();
        Fruit fruit;
        if(input == 1) {
            fruit = new Strawberry();
            fruit.show();
        } else {
            fruit = new Banana();
            fruit.show();
        }
    }

}
