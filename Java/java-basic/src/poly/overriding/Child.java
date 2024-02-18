package poly.overriding;

public class Child extends Parent {
    public String value = "parent";

    @Override
    public void method() {
        System.out.println("Child.method");
    }
}
