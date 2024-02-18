package static2.ex;

public class MathArrayUtils {
    private static int MAX = 100000000;
    private static int MIN = 0;

    private MathArrayUtils() {
        // 인스턴스를 생성하지 못하게 막기
    }

    public static int sum(int[] values) {
        int total = 0;
        for (int a : values) {
            total += a;
        }
        return total;
    }

    public static double average(int[] values) {
        return (double) sum(values) / values.length;
    }

    public static int min(int[] values) {
        int value = MAX;
        for (int a : values) {
            if (a < MAX) value = a;
        }
        return value;
    }

    public static int max(int[] values) {
        int value = MIN;
        for (int a : values) {
            if (a > MIN) value = a;
        }
        return value;
    }

}
