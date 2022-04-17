package Java.Tutorial14.src;

public class Node {
    
    private int x;
    private int y;

    // getter
    public int getX() {
        return this.x;
    }

    // setter => 보안적인 측면에서 클래스 내부 변수에대한 직접 접근을 막는다.
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // 생성자 => 클래스와 동일한 이름을 가진다.
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node getCenter(Node other) {
        return new Node((this.x + other.getX()) / 2, (this.y + other.getY()) / 2);
    }
}
