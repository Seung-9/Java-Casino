package Casino.Player;

import Casino.Card.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*-----------------------------------------------------
    플레이어의 기능
    추가로 카드를 가져오기, 카드 오픈하기, 현재 보유중인 카드 불러오기
    카드를 가져오면 카드덱에서 해당 카드를 제거해야함.
    파일 입출력 기능 -> 로그인한 사용자의 이름.txt 파일 생성 ->
    해당 파일에 이름, 돈, 승률 저장 -> save,delete,load 기능
    로그인 정보가 들어있는 파일과는 별개
-------------------------------------------------------*/
public class Player {
    private ArrayList<Card> cards;
    private ArrayList<PlayerInfo> data;
    private String filename, username, total, result;
    private double count, win, draw;

    public Player() { // 세이브 파일이 없을 때
        money = 0;
        count = 0;
        win = 0;
        cards = new ArrayList<>();
        data = new ArrayList<>();
    }

    public Player(String username) { // 세이브 파일이 있을 때
        money = 0;
        this.username = username;
        cards = new ArrayList<>();
        data = new ArrayList<>();
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    private static int money;

    public double getDraw() {
        return draw;
    }

    public void setDraw(double draw) {
        this.draw = draw;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) { this.money = money; }

    public void add(PlayerInfo playerInfo) {
        data.add(playerInfo);
    } // 매개변수로 받은 전적 및 승률 저장

    public void addCard(Card card) { // 카드 뽑기
        this.cards.add(card);
    } // 매개 변수로 받은 카드의 정보를 cards에 저장

    public void printCard() { // 보유중인 카드 목록 보여주기
        System.out.println("현재 보유중인 카드 목록");
        for(Card card : cards) {
            System.out.println(card.toString());
        }
    }

    public void clear() {
        cards.clear();
    } // 플레이어의 가변배열 안에 들어있는 카드 정보를 초기화(초기화 시키지 않으면 다음 게임을 진행할 때 해당 카드들이 그대로 불러와짐)

    public int sumValue() { // 카드의 값을 더해주는 메서드
        int sum = 0;
        for (Card card : cards) sum += card.getCardvalue();
        return sum;
    }

    // txt 파일 읽기
    public void load(String username) {
        filename = "/Users/seunggu-sun/IdeaProjects/School/untitled/src/"+username+".txt";
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("저장된 파일이 없습니다. 게임 이용 후 파일을 저장을 먼저 해주세요");
            return;
        }
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                String name = stringTokenizer.nextToken();
                int money = Integer.parseInt(stringTokenizer.nextToken());
                String rate = stringTokenizer.nextToken();
                String record = stringTokenizer.nextToken();
                add(new PlayerInfo(name, money, rate, record));
                this.setMoney(money);
                this.setResult(rate);
                this.setTotal(record);
                if(record.equals("0")) {
                    this.setTotal(record);
                } else {
                    tokenRecord(record);
                }
            } // while
            bufferedReader.close();
            fileReader.close();
            System.out.println(filename + "을 성공적으로 불러왔습니다.");
        } catch (FileNotFoundException e) {
            System.out.println(filename + "을 읽어올 수 없습니다.");;
        } catch (IOException e) {
            System.out.println(filename + "을 읽어오는 도중 오류가 발생하였습니다.");
        }
    }

    // txt 파일 저장
    public void save(String username) {
        filename = "/Users/seunggu-sun/IdeaProjects/School/untitled/src/"+username+".txt";
        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for(PlayerInfo playerInfo : data) {
                bufferedWriter.write(playerInfo.getName() + ",");
                bufferedWriter.write(String.valueOf(playerInfo.getMoney() + ","));
                bufferedWriter.write(playerInfo.getRate() + ",");
                bufferedWriter.write(playerInfo.getRecord());
            }
            System.out.println("유저의 정보가 담긴 파일을 성공적으로 저장했습니다.");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(filename + " 을 저장하는 도중 오류가 발생하였습니다.");
        }
    }

    // 세이브파일 삭제
    public void deleteData() {
        filename = "/Users/seunggu-sun/IdeaProjects/School/untitled/src/"+username+".txt";
        File file = new File(filename);
        if(file.exists()){
            file.delete();
            System.out.println(filename + " 파일을 성공적으로 삭제했습니다.");
        }
        else System.out.println(filename + " 파일이 존재하지 않습니다.");
    }

    // 새로운 데이터를 저장
    public void saveData() {
        filename = "/Users/seunggu-sun/IdeaProjects/School/untitled/src/"+username+".txt";
        int money = getMoney();
        String rate = getResult();
        String record = getTotal();
        data.clear(); // 이미 저장되어있던 데이터들은 클리어
        data.add(new PlayerInfo(username, money, rate, record)); // 저장하는 시점의 데이터들을 저장
        save(username);
    }

    // 현재 머니 확인하기
    public int checkMoney() {
        return this.money;
    }

    // 머니 충전하기
    public int chargeMoney(int money) {
        if(money > 0 && money < 2000000000) {
            this.money += money;
            return 1;
        } else
            return -1;
    }

    // 머니 저장하기
    public void saveMoney(int money) {
        this.money = money;
    }

    // 0전0승0무0패에서 숫자들을 각각 읽어와 값을 설정해준다.
    // 이 작업을 해주지 않으면 숫자 데이터를 읽어오지 못하기 때문에 저장할 때 값이 바뀌어버림
    public void tokenRecord(String record) {
        double[] value = new double[4];
        int i = 0;
        StringTokenizer stringTokenizer = new StringTokenizer(record, "전승무패");
        while(stringTokenizer.hasMoreTokens()) {
            value[i] = Double.parseDouble(stringTokenizer.nextToken());
            ++i;
        }
        setCount(value[0]);
        setWin(value[1]);
        setDraw(value[2]);
    }

    public void myInfo() {
        System.out.println("유저 이름 : " + username);
        System.out.println("현재 잔액 : " + getMoney());
        System.out.println("승률 : " + getResult());
        System.out.println("전적 : " + getTotal());
    }
}
