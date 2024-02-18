package access.ex;

public class ShoppingCart {
    private Item[] items = new Item[10];
    private int itemCount;

    public void addItem(Item item) {
        // 검증 로직
        if (itemCount >= items.length) {
            System.out.println("장바구니가 가득 찼습니다.");
            return;
        }

        // 수행 로직
        items[itemCount] = item;
        itemCount++;
    }

    public void displayItems() {
        System.out.println("장바구니 상품 출력");
        int totalPrice = 0;

        for (int i = 0; i < itemCount; i++) {
            items[i].showItemInfo();
            totalPrice += items[i].getTotalPrice();
        }

        System.out.println("전체 가격 합:" + totalPrice);
    }


}
