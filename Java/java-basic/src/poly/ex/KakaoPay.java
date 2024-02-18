package poly.ex;

public class KakaoPay implements Pay {

    public KakaoPay() {
        System.out.println("카카오페이 시스템과 연결합니다.");
    }

//    @Override
//    public void connect() {
//        System.out.println("카카오페이 시스템과 연결합니다.");
//    }

    @Override
    public boolean payment(int amount) {
        System.out.println(amount + "원 결제를 시도합니다.");
        return true;
    }
}
