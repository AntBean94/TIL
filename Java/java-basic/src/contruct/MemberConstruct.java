package contruct;

public class MemberConstruct {
    String name;
    int age;
    int grade;

    // 추가
    MemberConstruct(String name, int age) {
//        System.out.println(); // this를 사용하려면 첫줄에서 사용해야하는 규칙이 있다.
        this(name, age, 50); // 변경
    }

    MemberConstruct(String name, int age, int grade) {
        System.out.println("생성자 호출" + "이름: " + name + ", 나이: " + age + ", 성적: " + grade);
        this.name = name;
        this.age = age;
        this.grade = grade;
    }
}
