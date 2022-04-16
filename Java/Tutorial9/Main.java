package Java.Tutorial9;

public class Main {
    // 사용자 정의 함수
    /**
     * 사용자 정의 함수는 정해진 특정한 기능을 수행하는 모듈을 의미하며 함수를 적절히
     * 사용하면 하나의 문제를 잘게 분해할 수 있다. 예를 들어 이진 탐색 트리는 삽입, 
     * 삭제, 순회 등 다양한 함수의 집합으로 구성된다. 만약 사용자 정의 함수가 없다면
     * 오직 메인 함수에서 모든 알고리즘을 처리해야 하는데 이는 작업의 효율성을 저하시킬 수
     * 있다. 또한 함수는 각각의 모듈로서 쉽게 수정되고 보완될 수 있다는 장점이 있다.
     */
    public static void main(String[] args) {
        
        // 3개의 수의 최대공약수를 찾는 함수 실행
        System.out.println("300, 200, 1400의 최대 공약수: " + gcd(300, 200, 1400));

        // 약수 중 k번째로 작은 수를 찾는 함수 실행
        int x = 153, y = 13;
        int result = findMinK(x, y);
        if(result == -1) {
            System.out.println("약수를 찾지 못했습니다.");
        } else {
            System.out.println(x + "의 " + y+ "번째 약수는: " + findMinK(x, y));
        }

        // 문자열에서 마지막 단어를 반환하는 함수 실행
        System.out.println(lastChar("안녕하세요하하"));

        // max()를 이용하여 최대값을 저장하는 함수 실행
        System.out.println("345, 567, 709중에서 가장 큰 수는: " + findMax(345, 567, 709));
    }

    // 사용자 정의 함수: 반환형, 함수명, 매개변수
    // 최대 공약수 찾는 함수(매개변수 3개)
    public static int gcd(int a, int b, int c) {
        int min;
        // 최솟값 찾기
        if(a > b) 
        {
            if(b > c)
            {
                min = c;
            }
            else{
                min = b;
            }
        }
        else
        {
            if(a > c)
            {
                min = c;
            }
            else
            {
                min = a;
            }
        }
        // 최솟값의 약수 찾기
        for(int i = min; i > 0; i--) {
            if(a % i == 0 && b % i == 0 && c % i == 0) {
                return i;
            }
        }
        // 최대공약수가 없는 경우 -1반환
        return -1;

    }

    // 약수 중 K번째로 작은 수를 찾는 프로그램
    public static int findMinK(int number, int k) {
        for(int i = 1; i <= number; i++) {
            if(number % i == 0) {
                k--;
                if(k == 0) {
                    return i;
                }
            }
        }
        // k번째 약수가 존재하지 않는 경우
        return -1;
    }

    // 문자열에서 마지막 문자를 반환하는 함수
    public static char lastChar(String input) {
        return input.charAt(input.length() - 1);
    }

    // max()를 이용하여 최대값을 저장하는 함수
    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // max함수를 활용해 여러 수의 대소비교를 실행하는 함수
    public static int findMax(int a, int b, int c) {
        int result = max(a, b);
        result = max(result, c);
        return result;
    }
}