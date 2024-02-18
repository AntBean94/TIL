package ref;

public class MethodChange2 {
    public static void main(String[] args) {
        Data dataA = new Data();
        dataA.value = 10;
        System.out.println("변경전 dataA.value = " + dataA.value);
        changeReference(dataA, 30);
        System.out.println("변경후 dataA.value = " + dataA.value);
    }

    static void changeReference(Data dataX, int x) {
        dataX.value = x;
    }
}
