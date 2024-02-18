package poly.ex;

import java.util.HashMap;

public class PayService {
    private String option;
    private int amount;
//    private HashMap<String, Pay> payHashMap = new HashMap<>();
//
//    public PayService() {
//        payHashMap.put("kakao", new KakaoPay());
//        payHashMap.put("naver", new NaverPay());
//    }


    public void processPay(String option, int amount) {
        System.out.println("결제를 시작합니다: option=" + option + ", amount=" + amount);
//        if (payHashMap.containsKey(option)) {
//            Pay pay = payHashMap.get(option);
//            pay.connect();
//            if (pay.payment(amount)) System.out.println("결제가 성공했습니다.");
//            else System.out.println("결제가 실패했습니다.");
//        } else {
//            System.out.println("결제 수단이 없습니다.");
//            System.out.println("결제가 실패했습니다.");
//        }

        Pay pay = PayStore.findPay(option);
        boolean result = pay.payment(amount);

        if (result) System.out.println("결제가 성공했습니다.");
        else System.out.println("결제가 실패 했습니다.");


    }
}
