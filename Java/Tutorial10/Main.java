package Java.Tutorial10;

public class Main {
    // 반복함수와 재귀 함수

    public static void main(String[] args) {

        // 반복문으로 작성한 팩토리얼
        System.out.println("10 팩토리얼은: " + factorial(10));
        // 재귀함수로 작성한 팩토리얼
        System.out.println("10 팩토리얼은: " + factorialRecur(10));

    }

    // 반복함수(factorial)
    public static int factorial(int number) {
        int sum = 1;
        for(int i = 2; i <= number; i++) {
            sum *= i;
        }
        return sum;
    }

    // 재귀함수(factorial)
    public static int factorialRecur(int number) {
        if(number == 1) {
            return 1;
        } else {
            return number * factorialRecur(number - 1);
        }
    }

}
