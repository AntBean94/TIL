package enumeration.ex0;

public class StringGradeEx0_2 {
    public static void main(String[] args) {
        int price = 10000;

        DiscountService discountService = new DiscountService();

        // 존재하지 않는 등급
        int vip = discountService.discount("VIP", price);
        System.out.println("VIP 등급의 할인 금액: " + vip);

        // 오타
        int diamondd = discountService.discount("DIAMONDD", price);
        System.out.println("DIAMONDD 등급의 할인 금액: " + vip);

        // 소문자
        int gold = discountService.discount("gold", price);
        System.out.println("gold 등급의 할인 금액: " + vip);

        // 위의 케이스 처럼 개발 단계에서 드러나지 않는 버그를 내포할 가능성이 존재함.

        // String 사용 시 타입 안정성 부족 문제
        // 값의 제한 부족: String으로 상태나 카테고리를 표현하면, 잘못된 문자열을 실수로 입력할 가능성이 있다. 예를 들어, "Monday", "Tuesday"등을 나타내는 데 String을 사용한다면, 오타나 잘못된 값이 입력될 위험이 있다.
        // 컴파일 시 오류 감지 불가: 이러한 잘못된 값은 컴파일 시에는 감지되지 않고, 런타임에서만 문제가 발견되기 때문에 디버깅이 어려워질 수 있다.
    }
}
