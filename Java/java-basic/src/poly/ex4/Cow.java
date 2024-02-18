package poly.ex4;

public class Cow extends AbstractAnimal {
    @Override
    public void sound() {
        System.out.println("음머 음머");
    }

    @Override
    public void move() {
        System.out.println("송아지 이동");
    }
}
