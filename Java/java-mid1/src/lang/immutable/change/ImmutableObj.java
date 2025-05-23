package lang.immutable.change;

public class ImmutableObj {
    private final int value;

    public ImmutableObj(int value) {
        this.value = value;
    }

    public ImmutableObj add(int addValue) {
        int newValue = this.value + addValue;
        return new ImmutableObj(newValue);
    }

    public int getValue() {
        return value;
    }
}
