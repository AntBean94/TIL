public class Archer {

    String name;
    String power;

    public Archer(String name, String power) {
        this.name = name;
        this.power = power;
    }

    public boolean equals(Object obj) {
        // Object 클래스를 Archer 클래스로 바꿀수 있는 이유는 Object 클래스가
        // 모든 클래스의 부모 클래스기 때문이다.(다형성과 밀접한 관련)
        Archer temp = (Archer) obj;
        if(name == temp.name && power == temp.power) {
            return true;
        } else {
            return false;
        }
    }
}
