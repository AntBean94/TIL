package Java.Tutorial12;

import java.util.Scanner;

public class Main {
    // 배열
    /**
     * 자바에서 배열은 다음과 같이 선언한다.
     * int[] array = new int[100];
     */

    static Scanner sc = new Scanner(System.in);

    static void closeScanner() {
        sc.close();
    }

    public static void main(String[] args) {

        // 원하는 개수만큼의 배열 생성 및 최댓값을 구하는 프로그램
        makeArr();
        // 100개의 랜덤 정수의 평균을 구하는 프로그램
        randomArr();
    }

    
    // 원하는 개수만큼의 배열 생성 및 최댓값을 구하는 프로그램
    public static void makeArr() {
        
        System.out.print("생성할 배열의 크기를 입력하세요.: ");
        int number = sc.nextInt();
        int[] array = new int[number];
        for(int i = 0; i < number; i++) {
            System.out.print("배열에 입력할 정수를 하나씩 입력하세요.: ");
            array[i] = sc.nextInt();
        }
        int result = -1;
        for(int i = 0; i < number; i++) {
            result = max(result, array[i]);
        }
        System.out.println("입력한 정수 중 가장 큰 값은: " + result);

        closeScanner();
    }

    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // 100개의 랜덤 정수의 평균을 구하는 프로그램
    public static void randomArr() {
        int[] array = new int[100];
        for(int i = 0; i < 100; i++) {
            // Math.random은 0 ~ 0.xxx 값을 랜덤하게 가져온다 (0 <= x < 1)
            array[i] = (int) (Math.random() * 100 + 1);  // 1 <= x <= 100
        }
        int sum = 0;
        for(int i = 0; i < 100; i++) {
            sum += array[i];
        }
        System.out.println("100개의 랜덤 정수의 평균 값은: " + sum / 100 + "입니다.");
    }
}
