package poly.ex6;

public class SoundFlyMain {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Bird bird = new Bird();
        Chicken chicken = new Chicken();

        soundAnimal(dog);
        soundAnimal(bird);
        soundAnimal(chicken);

        flyAnimal(bird);
        flyAnimal(chicken);
        flyAnimal(dog);
    }

    // AbstractAnimal 사용 가능
    private static void soundAnimal(AbstractAnimal animal) {
        System.out.println("동물 소리 테스트 시작");
        animal.sound();
        System.out.println("동물 소리 테스트 종료");
    }

    // Fly 인터페이스가 있으면 사용 가능
    private static void flyAnimal(AbstractAnimal animal) {
        if (!(animal instanceof Fly)) return;
        System.out.println("날기 테스트 시작");
        ((Fly) animal).fly();
        System.out.println("날기 테스트 종료");
    }
}
