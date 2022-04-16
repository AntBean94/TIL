package Java.Tutorial6;

public class Main {
    // 조건문과 반복문

    final static int N = 30;

    final static int M = 15;
    public static void main (String[] args) {
        
        // 조건문
        String a = "I Love You";
        if(a.contains("Love")) {
            // 포함하는 경우 실행
            System.out.println("Me too");
        } else {
            // 포함하지 않는 경우 실행
            System.out.println("I Hate You");
        }

        int score = 95;

        if(score >= 90) {
            System.out.println("A+ 입니다.");
        } else if(score >= 80) {
            System.out.println("B+ 입니다.");
        } else if(score >= 70) {
            System.out.println("C+ 입니다.");
        } else {
            System.out.println("D+ 입니다.");
        }
        
        // 비교 연산
        String gender = "Man";
        int t = 0;

        // 자바는 String을 비교할 때 equal()을 이용한다.
        // String은 다른 자료형과 다른 문자열 자료형이기 때문.
        if(gender.equals("Man")) {
            System.out.println("남자입니다.");
        } else {
            System.out.println("여자입니다.");
        }
        
        if(t == 3) {
            System.out.println("t는 3입니다.");
        } else {
            System.out.println("t는 3이 아닙니다.");
        }

        // 대소문자를 무시하고 비교하는 경우
        if(gender.equalsIgnoreCase("man")) {
            System.out.println("남자입니다.");
        } else {
            System.out.println("여자입니다.");
        }

        // 반복문
        // while
        int i = 1, sum = 0;
        while(i <= 1000) {
            sum += i++;
        }
        System.out.println("1부터 1000까지의 합은 " + sum + "입니다.");

        // for문: 초기화 부분, 조건 부분, 연산 부분
        for(int j = N; j > 0; j--) {
            for(int k = j; k > 0; k--) {
                System.out.print("*");
            }
            System.out.println();
        }

        // for문을 활용하여 원을 출력하기
        // 원의 방정식: x^2 + y^2 = r^2
        for(int s = 0; s <= 30; s++) {
            for(int k = 0; k <= 30; k++) {
                if(Math.pow(s - M, 2) + Math.pow(k - M, 2) <= Math.pow(M, 2)) {
                    System.out.print(" *");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        
    }
}
