package lang.object.poly;

public class ObjectPolyExample2 {
    public static void main(String[] args) {
        Car car = new Car();
        Dog dog = new Dog();
        Object object = new Object();

        Object[] objects = {car, dog, object};

        size(objects);
    }

    private static void size(Object[] objects) {
        System.out.println(objects.length);
    }
}
