package Casino.Card;
/*-------------------------------------------------
    카드 클래스의 정보들을 가변 배열에 저장해주는 클래스(중복제거)
---------------------------------------------------*/

import java.util.ArrayList;

// CardDeck 클래스는 서로 다른 52장의 카드를 생성해주는 것이다.
public class CardDeck { // 52개의 카드 정보를 cards에 저장
    ArrayList<Card> cards;
    private String[] shape = {"♠", "♣", "♥", "◆"};
    final static int COUNT = 13;

    public CardDeck() {
        cards = new ArrayList<>();
    }

    public void makeCard() { // 카드 객체를 생성해서 가변배열에 저장
        for(int i = 0; i < shape.length; i++) {
            for(int j = 0; j < COUNT; j++) {
                Card card = new Card(shape[i], j);
                cards.add(card);
            }
        }
    }

    public Card selectCard() { // 랜덤으로 카드를 제공
        int size = cards.size();
        int pos = (int) (Math.random() * size);
        Card selectedCard = cards.get(pos);
        cards.remove(pos); // 제공된 카드는 가변배열에서 지운다.(중복을 방지)
        return selectedCard;
    }
}
