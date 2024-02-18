package poly.ex;

public class PayStore {
    public static Pay findPay(String option) {
        if (option.equals("naver")) {
            return new NaverPay();
        } else if (option.equals("kakao")) {
            return new KakaoPay();
        } else {
            return new DefaultPay();
        }
    }
}
