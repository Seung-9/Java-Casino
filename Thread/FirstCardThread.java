package Casino.Thread;

import Casino.Card.Card;
import Casino.Card.CardDeck;
import Casino.Player.Dealer;

public class FirstCardThread implements Runnable{
    private static String firstCard;

    public FirstCardThread(Dealer dealer, CardDeck carddeck) {
        Card card;
        card = carddeck.selectCard(); //카드덱에서 카드 한 장을 뽑는다.
        dealer.addCard(card); // 뽑은 카드의 정보를 딜러의 카드 정보에 넣는다.
        firstCard = "모양 : " + card.getShape() + ", 숫자 : " + card.getNumber();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
            System.out.println("카드를 한 장 뽑습니다.");
            Thread.sleep(200);
            System.out.println("첫 번쨰 카드를 오픈합니다.");
            Thread.sleep(200);
            System.out.println(firstCard);
            Thread.sleep(200);
            System.out.println("다음을 선택해 주십시오.");
            Thread.sleep(200);
            System.out.println("1. 홀수, 2. 짝수");

        } catch(Exception e) {
            return;
        }
    }
}
