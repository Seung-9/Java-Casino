package Casino.Manager;

// Rule 클래스의 기능
// player vs dealer(computer) 중 승자를 파악한다.
// 보유 머니 계산, 승률 계산

import Casino.Player.Player;

public class Rule {

    // 블랙잭 승리 조건
    public int getWinner(int dealerSum, int playerSum) {
        if(dealerSum > 21 && playerSum <= 21) {
            return 1; // 딜러 21초과 사용자 win
        } else if(dealerSum <= 21 && playerSum > 21) {
            return -1; //사용자 21초과 딜러 win
        } else if(dealerSum > 21 && playerSum > 21) {
            return 0; // 둘 다 21초과 무승부
        } else if(dealerSum <= 21 && playerSum <= 21){
            int dealer = 21 - dealerSum;
            int player = 21 - playerSum;
            if(dealer > player) return 1; // 사용자 win
            else if(dealer == player)   return 0;
            else    return -1;
        } else
            return -5;
    }

    // 홀짝게임 승리 조건
    public int snifWinner(int sum, int select) {
        int answer;
        if(sum % 2 == 0)    answer = 2;
        else    answer = 1;

        if(answer == select)   return 1;
        else    return -1;
    }

    // 보유 머니 계산
    public int calcMoney(int winner, int batmoney, Player player) {
        if(winner == 1) {
            return (player.checkMoney() + (batmoney * 2)); // 유저가 이기면 돈 2배

        } else if(winner == -1) {
            return player.checkMoney(); // 유저가 지면 돈 몰수
        }
        else
            return player.checkMoney() + batmoney; // 비기면 배팅금액 그대로 돌려줌
    }

    // 승률 계산하기
    public String calcRate(double count, double win) {
        String result = (Math.round(((win / count)*100)*100/100.0)) + "%";
        return result;
    }
}
