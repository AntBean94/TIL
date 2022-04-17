public class Main {
    // 객체지향의 활용
    public static void main(String[] args) {

        Hero[] heroes = new Hero[3];
        heroes[0] = new Warrior("전사");
        heroes[1] = new Archer("궁수");
        heroes[2] = new Wizard("마법사");

        for(int i = 0; i < 3; i++) {
            heroes[i].attack();

            if(heroes[i] instanceof Warrior) {
                Warrior temp = (Warrior) heroes[i];
                temp.groundCutting();
            } else if(heroes[i] instanceof Archer) {
                Archer temp = (Archer) heroes[i];
                temp.fireArrow();
            } else if(heroes[i] instanceof Wizard) {
                Wizard temp = (Wizard) heroes[i];
                temp.freezing();
            }
        }
    }
}
