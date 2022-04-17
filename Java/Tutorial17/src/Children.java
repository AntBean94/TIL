// Parent 에 final선언시 에러 발생

//public class Children extends Parent {
public class Children {

    // 함수 재정의 => parent의 show에 final을 선언하면 에러발생
    public void show() {
        System.out.println("Hello");
    }

    static void test() {
        Children children = new Children();
        children.show();
    }
}
