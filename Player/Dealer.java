package Casino.Player;

import Casino.Card.Card;

import java.util.ArrayList;
/*-----------------------------------------------------
 딜러의 기능(컴퓨터)
 처음에 2장의 카드를 뽑는다.
 2장의 카드의 합계가 16 이하면 반드시 한 장의 카드를 뽑아야함
 2장의 카드의 합계가 17 이상이면 카드를 더이상 뽑을 수 없다.
-------------------------------------------------------*/
public class Dealer {
    ArrayList<Card> cards;

    public Dealer() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) { // 2장의 카드 뽑기(반드시 2장은 뽑아야 하기 때문에 따로 만듬)
        if (isValue() == 1) {
            this.cards.add(card);
        } else {
            System.out.println("딜러 카드의 총 합이 17 이상입니다.");
        }
    }
    public void clear() { // 플레이어의 가변배열 안에 들어있는 카드 정보를 초기화(초기화 시키지 않으면 다음 게임을 진행할 때 해당 카드들이 그대로 불러와짐)
        cards.clear();
    }

    public int isValue() { // 2장의 카드 합이 16 이한지 이상인지 판단하는 메서드.
        if (sumValue() <= 16) return 1;
        else return 0;
    }

    public int sumValue() { // 카드 값의 합을 반환해주는 메서드
        int sum = 0;
        for (Card card : cards) sum += card.getCardvalue();
        return sum;
    }

    public void printCard() { // 보유중인 카드 목록 보여주기
        System.out.println("컴퓨터가 보유한 카드 목록");
        for (Card card : cards) {
            System.out.println(card.toString());
        }
    }
}
