package Casino.Player;

/*------------------------------
    로그인한 사용자의 게임 정보 클래스
    이름, 승률, 전적
-------------------------------*/
public class PlayerInfo {
    private String name;
    private String rate;
    private String record;
    private int money;

    public String getRecord() {
        return record;
    }

    public String getRate() {
        return rate;
    }

    public PlayerInfo(String name, int money, String rate, String record) {
        this.name = name;
        this.money = money;
        this.rate = rate;
        this.record = record;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return this.name;
    }
}
