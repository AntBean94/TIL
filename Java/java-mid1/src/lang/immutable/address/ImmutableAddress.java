package lang.immutable.address;

public class ImmutableAddress {
    // 최초로 생성된 이후 변경을 막기 위해 필드를 final로 선언
    private final String value;

    public ImmutableAddress(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // setter가  없음

    @Override
    public String toString() {
        return "Address{" +
                "value='" + value + '\'' +
                '}';
    }
}
