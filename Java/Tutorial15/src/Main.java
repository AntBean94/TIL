import java.util.Scanner;

public class Main {
    // 상속
    /**
     * 상속이란 쉽게 말해서 다른 클래스가 가지고 있는 정보를 자신이 포함하겠다는 의미이다.
     * 즉, 다른 클래스에 대한 정보를 상속받아 자신이 그대로 사용할 수 있도록 한다.
     * 상속을 적절히 활용하면 불필요한 코드의 수를 줄일 수 있어서 상당히 효율적으로
     * 프로그램을 개발할 수 있다.
     */
    public static void main(String[] args) {

        Student student1 = new Student("gaudi", 27, 171, 68, "20220417", 1, 4.5);
        Student student2 = new Student("kong", 24, 164, 53, "20220416", 4, 3.9);
        student1.show();
        student2.show();

        Teacher teacher1 = new Teacher("John Doe", 40, 190, 100, "abc1234", 3000000, 13);
        teacher1.show();

//        Student[] students = new Student[100];
//        for(int i = 0; i < 100; i++) {
//            students[i] = new Student("홍길동", 21, 174, 70, i + " ", 1, 4.3);
//            students[i].show();
//        }

        Scanner sc = new Scanner(System.in);
        System.out.print("총 몇명의 학생이 존재합니까? ");
        int number = sc.nextInt();
        Student[] students = new Student[number];
        for(int i = 0; i < number; i++) {
            String name;
            int age;
            int height;
            int weight;
            String studentID;
            int grade;
            double GPA;
            System.out.print("학생의 이름을 입력하세요: ");
            name = sc.next();
            System.out.print("학생의 나이를 입력하세요: ");
            age = sc.nextInt();
            System.out.print("학생의 키를 입력하세요: ");
            height = sc.nextInt();
            System.out.print("학생의 몸무게를 입력하세요: ");
            weight = sc.nextInt();
            System.out.print("학생의 학번을 입력하세요: ");
            studentID = sc.next();
            System.out.print("학생의 학년을 입력하세요: ");
            grade = sc.nextInt();
            System.out.print("학생의 학점을 입력하세요: ");
            GPA = sc.nextDouble();

            students[i] = new Student(name, age, height, weight, studentID, grade, GPA);
        }
        for(int i = 0; i < number; i++) {
            students[i].show();
        }
    }
}
