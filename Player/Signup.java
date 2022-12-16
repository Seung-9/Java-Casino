package Casino.Player;
/*------------------------------
회원가입 정보를 정의하는 클래스
 -------------------------------*/
public class Signup { // 아이디, 패스워드, 유저네임
    private String id;
    private String password;
    private String username;

    public Signup(String id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}