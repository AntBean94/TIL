package extends1.super2;

public class ClassC extends ClassB {
    public ClassC() {
//        super();  // 기본 생성자가 아님
        super(10, 20);
        System.out.println("ClassC 생성자");
    }
}
