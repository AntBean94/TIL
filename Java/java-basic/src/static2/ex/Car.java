package static2.ex;

public class Car {
    private String name;
    private static int totalCount;  // 스태틱 변수

    public Car(String name) {
        this.name = name;
        totalCount++;
        System.out.println("차량 구입, 이름: " + name);
    }
    public static void showTotalCars() {
        System.out.println("구매한 차량 수: " + totalCount);
    }
}
