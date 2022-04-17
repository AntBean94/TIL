public class Main {
    // 객체(Object)
    /**
     * 객체(Object) 클래스는 모든 객체의 조상으로서 쓰인다. 자바에서 모든 클래스는
     * 암시적으로 Object 클래스를 상속받고 있다. 그러한 이유로 Object 클래스는 모든
     * 클래스의 조상이라고 할 수 있다. 이러한 클래스가 존재하는 이유는 모든 클래스가
     * 공통으로 포함하고 있어야 하는 기능을 제공하기 위해서이다.
     */

    public static void main(String[] args) {

        Archer archer1 = new Archer("궁수1", "상");
        Archer archer2 = new Archer("궁수1", "상");
        System.out.println(archer1 == archer2);         // false (인스턴스가 다름)
        System.out.println(archer1.equals(archer2));    // true (값이 같음)

    }
}
