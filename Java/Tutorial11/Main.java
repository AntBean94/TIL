package Java.Tutorial11;

public class Main {
    
    public static void main(String[] args) {

        // 피보나치 수열(재귀)
        System.out.println("피보나치 수열의 8번째 수는: " + fiboRecur(8));
        // 피보나치 수열(반복)
        System.out.println("피보나치 수열의 90번째 수는: " + fibo(90));
    }

    // k번째 피보나치 수열을 찾는 함수(재귀)
    public static int fiboRecur(int k) {
        if(k == 1 || k == 2) {
            return 1;
        } else {
            return fiboRecur(k - 1) + fiboRecur(k - 2);
        }
    }

    // k번째 피보나치 수열을 찾는 함수(반복문)
    public static long fibo(int k) {
        long a = 1, b = 1;
        if (k < 3) return 1;
        for(int i = 3; i <= k; i++) {
            long tmp = b;
            b = a + tmp;
            a = tmp;
        }
        return b;
    }
}
