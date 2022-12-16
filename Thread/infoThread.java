package Casino.Thread;

import Casino.Player.Player;
import Casino.Manager.Rule;

public class infoThread implements Runnable  {
    private static Rule rule = new Rule();
    private static String s;
    private static String record;

    public infoThread(Player player, double count, double win, double draw) {
        player.setCount(count); // Player class의 count 값을 저장(안 해주면 값이 계속해서 초기화됨)
        player.setWin(win); // 승리 횟수 저장
        player.setDraw(draw); // 무승부 횟수 저장

        String result = rule.calcRate(count, win);
        player.setResult(result); // 승률 저장

        record = (int) player.getCount() + "전" + (int) player.getWin() + "승" + (int) player.getDraw() + "무" + (int) (player.getCount() - player.getWin()) + "패\n";
        player.setTotal(record); // 전적 저장

        s = "사용자님의 승률은 " + player.getResult() + "입니다.\n"; // ressult 값을 가져와 승률이 몇 프론지 출력
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            for(int i = 0; i < record.length(); i++) {
                System.out.print(record.charAt(i));
                Thread.sleep(100);
            }
            for(int i = 0; i < s.length(); i++) {
                System.out.print(s.charAt(i));
                Thread.sleep(100);
            }
        } catch (Exception e) {
            return;
        }
    }
}