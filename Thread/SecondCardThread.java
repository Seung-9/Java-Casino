package Casino.Thread;

import Casino.Card.Card;
import Casino.Card.CardDeck;
import Casino.Player.Dealer;

public class SecondCardThread implements Runnable {
    private static String secondCard;

    public SecondCardThread(Dealer dealer, CardDeck carddeck) {
        Card card;
        card = carddeck.selectCard(); //카드덱에서 카드 한 장을 뽑는다.
        dealer.addCard(card); // 뽑은 카드의 정보를 딜러의 카드 정보에 넣는다.
        secondCard = "모양 : " + card.getShape() + ", 숫자 : " + card.getNumber();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
            System.out.println("선택을 완료헀습니다.");
            Thread.sleep(200);
            System.out.println("두 번째 카드를 뽑습니다.");
            Thread.sleep(1500);
            System.out.println("두 번쨰 카드를 오픈합니다.");
            Thread.sleep(2500);
            System.out.println(secondCard);
            Thread.sleep(1000);

        } catch(Exception e) {
            return;
        }
    }
}
