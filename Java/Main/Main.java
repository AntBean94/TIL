package Java.Main;

public class Main {
    public static void main (String[] args) {

        int time = 1000;
        int minute = time / 60;
        int second = time % 60;
        System.out.println(minute + "분 " + second + "초");

        // 증감 연산자
        int a = 1;
        System.out.println(a);
        a++;
        System.out.println(a);
        System.out.println(++a);

        // 모듈로 연산
        System.out.println(1 % 3);
        System.out.println(2 % 3);
        System.out.println(3 % 3);
        System.out.println(4 % 3);
        System.out.println(5 % 3);
        System.out.println(6 % 3);
        
        // 논리 연산자
        int x = 50;
        int y = 50;
        System.out.println("x가 y와 같은가요? " + (x == y));
        System.out.println("x가 y보다 큰가요? " + (x > y));
        System.out.println("x가 y보다 작은가요? " + (x < y));
        System.out.println("x가 y와 같으면서 30보다 큰가요? " + ((x == y) && (x > 30)));
        
        // max 함수 실행
        System.out.println("최댓값은 " + max(x, y) + "입니다.");

        // pow
        double t = Math.pow(3.0, 20.0);
        System.out.println("3의 20제곱은 " + (int) t + "입니다.");
    }

    // 삼항 연산자
    // 함수: 반환형, 함수 이름, 매개 변수
    static int max(int a, int b) {
        int result = (a > b) ? a : b;
        return result;
    }
}
