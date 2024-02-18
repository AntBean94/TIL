package poly.ex3;

// 이름이 반드시 Abstract로 시작할 필요는 없음
public abstract class AbstractAnimal {

    public abstract void sound();

    public void move() {
        System.out.println("동물이 움직입니다.");
    }
}
