package enumeration.ex3;

public class EnumRefMain {
    public static void main(String[] args) {
        System.out.println("Class BASIC = " + ClassGrade.BASIC.getClass());
        System.out.println("Class GOLD = " + ClassGrade.GOLD.getClass());
        System.out.println("Class DIAMOND = " + ClassGrade.DIAMOND.getClass());

        System.out.println("ref BASIC = " + refValue(ClassGrade.BASIC));
        System.out.println("ref GOLD = " + refValue(ClassGrade.GOLD));
        System.out.println("ref DIAMOND = " + refValue(ClassGrade.DIAMOND));

    }

    private static String refValue(Object grade) {
        return Integer.toHexString(System.identityHashCode(grade));
    }
}
