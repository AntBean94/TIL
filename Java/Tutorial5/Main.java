package Java.Tutorial5;

public class Main {
    // i++와 ++i는 단순히 값을 증가시키는 목적이라면 그 기능이 동일하다.
    // 100 < x < 200은 잘못된 표현식(python에서는 허용).
    // 올바르게 표현하려면 (100 < x) && (x < 200)로 표현한다.
    // i++는 i += 1 과 동일한 표현이다. 또한 i = i + 1과도 동일한 표현이다.

    public static void main (String[] args) {

        int i = 20;
        i++;
        System.out.println(i);
        i = i + 1;
        System.out.println(i);
        i += 1;
        System.out.println(i);
        // System.out.println(100 < i < 200); => error
        System.out.println((100 < i) && (i < 200));
    }

}
