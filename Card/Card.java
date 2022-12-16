package Casino.Card;

/*
    카드 정의 클래스
 */
public class Card {
    private String shape; // 모양
    private String number; // 숫자
    private int cardvalue; // 카드의 값(결과를 알기 위해서 필요함)

    public Card(String shape, int number) {
        this.shape = shape;
        this.number = this.checkNumber(number);
        this.cardvalue = this.cardValueCheck(number);
    };

    public String getShape() {
        return shape;
    }

    public String getNumber() {
        return number;
    }

    public int getCardvalue() {
        return cardvalue;
    }

    private String checkNumber(int number) { // A, J, Q, K 구분
        if(number == 0) {
            return "A";
        } else if(number == 10) {
            return "J";
        } else if(number == 11) {
            return "Q";
        } else if(number == 12) {
            return "K";
        } else
            return String.valueOf(number);
    }

    private int cardValueCheck(int number) { // 카드의 value 값을 반환
        if(number == 0)
            return 1;
        else if(number >= 10)
            return 10;
        else
            return number;
    }

    @Override
    public String toString() {
        return "모양 : " + shape + ", 숫자 : " + number;
    }

}