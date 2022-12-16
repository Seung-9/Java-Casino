package Casino.Manager;
/*----------------------------------------------
    게임 진행 관련 메서드
    sniffing Game은 스레드만을 사용해서 게임을 진행하였다.
    플레이어의 세이브파일에 저장된 데이터들을 모두 불러오고
    게임 진행 후에는 데이터들을 다시 세이브해준다.
 -----------------------------------------------*/

import Casino.Card.Card;
import Casino.Card.CardDeck;
import Casino.Player.Dealer;
import Casino.Player.Player;
import Casino.Thread.*;

import java.util.Scanner;

public class Game {
    private static Dealer dealer = new Dealer();
    private static CardDeck carddeck = new CardDeck();
    private static Rule rule = new Rule();

    public static void play(Player player) { // 블랙잭 게임 플레이 메서드
        String select1;
        int batmoney, select2;

        Scanner sc = new Scanner(System.in);

        double count = player.getCount(); // 판수
        double win = player.getWin(); // 이긴 횟수 (판수 - (이긴횟수 + 비긴횟수) = 진횟수)
        double draw = player.getDraw();

        player.clear(); // 이전에 사용한 Player카드 초기화
        dealer.clear(); // 이전에 사용한 Dealer카드 초기화
        carddeck.makeCard(); // 카드 생성

        System.out.println("게임을 선택해주세요.");
        System.out.println("1. BlackJack Game, 2. Sniffing Game");
        select2 = sc.nextInt();

        System.out.print("배팅할 금액을 입력하세요 : ");
        batmoney = sc.nextInt();
        if(player.getMoney() < batmoney) {
            System.out.println("머니가 부족합니다.");

        }

        System.out.println("배팅 후 잔액 : " + (player.checkMoney() - batmoney)); // 보유머니 - 배팅머니
        player.saveMoney(player.checkMoney() - batmoney); // 배팅 후 유저의 잔액

        // 블랙잭 게임
        if(select2 == 1) {
            Card card;
            System.out.println("카드 2장을 지급합니다.");
            for (int i = 0; i < 2; i++) {
                card = carddeck.selectCard(); // 카드 뽑기
                player.addCard(card); // 플레이어의 카드 리스트에 추가
                card = carddeck.selectCard(); // 카드 뽑기
                dealer.addCard(card); // 딜러의 카드 리스트에 추가
            }
            player.printCard();
            dealer.printCard();

            while (true) {
                while (dealer.isValue() == 1) {
                    card = carddeck.selectCard();
                    dealer.addCard(card);
                }
                System.out.println("카드를 뽑으시겠습니까?(y or n)");
                select1 = sc.next();
                // 카드를 더 뽑으면 player에게 카드를 add 해준다.
                if (select1.equals("y")) {
                    card = carddeck.selectCard();
                    player.addCard(card);
                    player.printCard();
                } else if(select1.equals("n")){ // n를 선택하면 player의 카드와 dealer의 카드를 오픈 후 결과를 출력한다.
                    // 게임이 종료
                    ++count;
                    System.out.println("결과를 내는중입니다.");
                    System.out.println("       .");
                    System.out.println("       .");

                    player.printCard(); // 최종 플레이어 카드 오픈
                    dealer.printCard(); // 최종 딜러 카드 오픈
                    int winner = rule.getWinner(dealer.sumValue(), player.sumValue()); // winner 변수에 승자를 구분짓는 값을 리턴.

                    System.out.println("사용자 카드의 총 합 : " + player.sumValue() + ", 컴퓨터 카드의 총 합 : " + dealer.sumValue());
                    if (winner == 1) { // 반환받은 리턴값에 따라 유저의 money를 저장
                        System.out.println("사용자가 이겼습니다.");
                        player.saveMoney(rule.calcMoney(winner, batmoney, player));
                        ++win;
                    } else if (winner == 0) {
                        System.out.println("비겼습니다.");
                        player.saveMoney(rule.calcMoney(winner, batmoney, player));
                        ++draw;
                    } else if (winner == -1) {
                        System.out.println("컴퓨터가 이겼습니다.");
                        player.saveMoney(rule.calcMoney(winner, batmoney, player));
                    } else {
                        System.out.println("계산중 오류가 발생하였습니다.");
                    }
                    player.setCount(count); // Player class의 count 값을 저장(안 해주면 값이 계속해서 초기화됨)
                    player.setWin(win); // 승리 횟수 저장
                    player.setDraw(draw); // 무승부 횟수 저장
                    String result = rule.calcRate(count, win);
                    player.setResult(result); // 승률 저장
                    String record = (int) player.getCount() + "전" + (int) player.getWin() + "승" + (int) player.getDraw() + "무" + (int) (player.getCount() - player.getWin()) + "패";
                    player.setTotal(record); // 전적 저장
                    System.out.println("사용자님의 승률은 " + player.getResult() + "입니다."); // System.out.println() -> ressult 값을 가져와 승률이 몇 프론지 출력
                    break;
                } // else
            } // while
        } // if
        else {
            ++count;
            int answer = 0;
            FontThread font = new FontThread();
            FirstCardThread firstCardThread = new FirstCardThread(dealer, carddeck);
            SecondCardThread secondCardThread = new SecondCardThread(dealer, carddeck);
            LoadingThread loadingThread = new LoadingThread();

            font.run(); // 홀짝 글씨 출력
            firstCardThread.run(); // 첫 번째 카드를 출력
            answer = sc.nextInt();

            secondCardThread.run(); // 두 번째 카드 출력

            int winner = rule.snifWinner(dealer.sumValue(), answer); // 승자 정하기

            loadingThread.run();
            System.out.println("컴퓨터 카드의 총 합 : " + dealer.sumValue());
            if (winner == 1) { // 반환받은 리턴값에 따라 유저의 money를 저장
                System.out.println("맞췄습니다.");
                player.saveMoney(rule.calcMoney(winner, batmoney, player));
                ++win;
            } else {
                System.out.println("틀렸습니다.");
                player.saveMoney(rule.calcMoney(winner, batmoney, player));
            }
            infoThread infoThread = new infoThread(player, count, win, draw);
            infoThread.run();
        } // else
    }
}