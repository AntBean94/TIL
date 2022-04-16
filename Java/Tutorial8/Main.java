package Java.Tutorial8;

import java.util.Scanner;

public class Main {
    // java 기초프로그래밍 과제
    public static void main(String[] args) {
        // 과제 실행
        problem1();
        problem2();
        // 정답: 20, 900, 15, 13, 17
        problem3();
    }

    // 과제 1
    // 자신의 이름을 출력하는 프로그램을 작성해보세요.
    public static void problem1() {
        System.out.print("이름을 입력해주세요.: ");
        String name = sc.next();
        System.out.println("제 이름은 " + name + "입니다.");
        closeScanner();
    }

    // 과제 2
    // 아래 프로그램의 실행 결과는?
    public static void problem2() {
        System.out.println(10 + 10);
        System.out.println(30 * 30);
        System.out.println(20 - 5);
        System.out.println(40 / 3);
        System.out.println(395 % 18);
    }

    // 과제 3
    // 원 모양을 출력하는 프로그램을 작성하시오.
    public static void problem3() {
        int N = 15;
        for(int i = -N; i <= N; i++) {
            for(int j = -N; j <= N; j++) {
                if (i * i + j * j <= N * N) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    static Scanner sc = new Scanner(System.in);

    static void closeScanner() {
        sc.close();
    }
}
