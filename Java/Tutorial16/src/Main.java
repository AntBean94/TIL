public class Main extends Player {
    // 추상
    /**
     * 자바 객체지향의 활용
     * 자바에서의 객체지향을 본격적으로 활용하기 위해서는 자바의 객체지향 개념을 더욱 더 깊게
     * 이해하고 적용할 필요가 있다. 자바에서는 C언어나 여타 원시적인 프로그래밍 언어에서는
     * 제공하지 않았던 특수한 기능들을 제공한다. 대표적으로 추상(Abstract)의 개념이 있으며
     * 그와 비슷하지만 조금 다른 개념인 인터페이스(Interface)의 개념이 존재한다. 자바에서는
     * 이러한 다양한 설계 기법들을 제공하기 때문에 개발 자체에서의 안정성과 및 확장 가능성을
     * 보장받을 수 있게 된다.
     */

    public static void main(String[] args) {

        Main main = new Main();
        main.play("Starry Eyes - The Weeknd");
        main.pause();
        main.stop();
    }

    // 추상클래서에서 선언한 함수를 반드시 구현해야 하므로 설계적인
    // 측면에서 도움을 받을 수 있다.

    @Override
    void play(String songName) {

        System.out.println(songName + " 곡을 재생합니다.");
    }

    @Override
    void pause() {

        System.out.println("곡을 일시정지합니다.");
    }

    @Override
    void stop() {

        System.out.println("곡을 정지합니다.");
    }
}
