package Casino;

import Casino.Manager.Game;
import Casino.Manager.LogManager;
import Casino.Player.Player;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static LogManager logManager = new LogManager("/Users/seunggu-sun/IdeaProjects/School/untitled/src/MemberDB.txt");

    public static void main(String[] args) {
        String username = logManager.login();
        int money = 0;
        Player player = new Player(username);
        player.load(username); // 최초 로그인시 자동으로 해당 유저의 세이브 파일을 불러온다
        while (true) {
            System.out.println("Casino World에 오신 것을 환영합니다.");
            System.out.println("1. 플레이 하기");
            System.out.println("2. 보유 머니 확인하기");
            System.out.println("3. 머니 충전하기");
            // System.out.println("4. 세이브 파일 불러오기");
            System.out.println("4. 세이브 파일 저장하기");
            System.out.println("5. 세이브 파일 삭제하기");
            System.out.println("6. 나의 정보 보기");
            System.out.println("0. 로그아웃");
            int menu = sc.nextInt();


            switch (menu) {
                case 0 -> {
                    username = logManager.login();
                    player = new Player(username);
                    player.load(username); // 로그인이 되면 자동으로 해당 유저의 세이브 파일을 불러옴
                }
                case 1 -> {
                    Game game = new Game();
                    game.play(player);
                }

                case 2 -> {
                    System.out.println("현재 머니 : " + player.checkMoney());
                }
                case 3 -> {
                    System.out.print("충전할 금액을 입력하세요 : ");
                    money = sc.nextInt();
                    int chk = player.chargeMoney(money);
                    if(chk == 1) {
                        System.out.println("충전이 완료되었습니다.");
                        break;
                    }
                    else {
                        System.out.println("충전 중 오류가 발생하였습니다.");
                        continue;
                    }
                }
                // case 4 -> player.loadData();
                case 4 -> player.saveData();
                case 5 -> player.deleteData();
                case 6 -> player.myInfo();
            } // switch
        } // while
    } // psvm
}