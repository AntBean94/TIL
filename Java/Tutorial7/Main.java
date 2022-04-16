package Java.Tutorial7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    // 기본 입출력
    /**
     * 자바에서는 대표적으로 Scanner 클래스를 이용하여 사용자와 상호작용할 수 있다.
     * 일반적으로 Scanner sc = new Scanner(System.in);
     * 으로 클래스 객체를 생성한 뒤에 sc nextInt();와 같은 방법으로 int형을
     * 입력받을 수 있다. 입력받은 자료는 내부적으로 어떠한 처리를 한 뒤에 다시 사용자에게
     * 그 값을 반환할 수 있다. 프로그램이 입출력을 잘 지원한다는 것은 사용자 인터페이스가
     * 뛰어나다는 의미와 같다.
     * GUI를 활용하면 이러한 입출력을 훨씬 용이하게 할 수 있다.
     */

    public static void main(String[] args) {
        // 스캐너 클래스 사용
        System.out.print("정수를 입력하세요: ");
        // 사용자 입력부
        int i = sc.nextInt();
        System.out.println("입력된 정수는 " + i + "입니다.");
        // 입력부 닫기(반드시 닫아야하는지는 고민이 필요함)
        // scannerClose();

        File file = new File("input.txt");
        // 자바는 파일을 읽어오는 경우 파일이 없는 상황에 대비해 예외처리를 반드시 
        // 해주지 않으면 코드 작성단계에서 에러를 반환한다.
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextInt()) {
                System.out.println(sc.nextInt() * 100);
            }
            scannerClose();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 읽어오는 도중에 오류가 발생했습니다.");
        }

    }

    static Scanner sc = new Scanner(System.in);

    static void scannerClose() {
        sc.close();
    }
}
