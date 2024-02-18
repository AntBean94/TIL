package static1;

public class DataCountMain3 {
    public static void main(String[] args) {
        Data3 a = new Data3("A");
        System.out.println("A count=" + Data3.count);

        Data3 b = new Data3("B");
        System.out.println("B count=" + Data3.count);

        Data3 c = new Data3("C");
        System.out.println("C count=" + Data3.count);

        // 추가
        // 인스턴스를 통한 접근 (결국 클래스 변수의 위치를 참조한다는 점에서 클래스를 통한 접근방식과 같다.)
        Data3 d = new Data3("D");
        System.out.println(d.count);

        // 클래스를 통한 접근
        System.out.println(Data3.count);

        // 정적변수를 참조한다는 점을 명확히 하기 위해 클래스를 통한 접근 방식을 사용하자.
    }
}
