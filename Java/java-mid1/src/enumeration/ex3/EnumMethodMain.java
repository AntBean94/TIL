package enumeration.ex3;

import java.util.Arrays;

public class EnumMethodMain {
    public static void main(String[] args) {

        ClassGrade[] grades = ClassGrade.values();
        System.out.println("grades = " + Arrays.toString(grades));

        for (ClassGrade grade : grades) {
            System.out.println("grade 순서 = " + grade + ":" + grade.ordinal());
        }

        // String => Enum 변환 가능, 잘못된 문자면 IllegalArgumentException 발생
        String gold = "GOLD";
        ClassGrade goldEnum = ClassGrade.valueOf(gold);
        System.out.println("gold = " + gold);

        // Enum의 ordinal 메서드는 가급적 로직안에서 사용하지 말자
        // 예기치 않은 버그를 발생시킬 수 있음



    }
}
