package enumeration.ex3;

public class EnumEx3_1 {
    public static void main(String[] args) {
        int price = 10000;
        DiscountService discountService = new DiscountService();

        int basicPrice = discountService.discount(ClassGrade.BASIC, price);
        int goldPrice = discountService.discount(ClassGrade.GOLD, price);
        int diamondPrice = discountService.discount(ClassGrade.DIAMOND, price);

        System.out.println("BASIC 등급의 할인 금액: " + basicPrice);
        System.out.println("GOLD 등급의 할인 금액: " + goldPrice);
        System.out.println("DIAMOND 등급의 할인 금액: " + diamondPrice);
    }
}
