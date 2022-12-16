package Casino.Manager;

import Casino.Player.Player;
import Casino.Player.PlayerInfo;
import Casino.Player.Signup;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*----------------------------------------------------------------------------------------
로그인, 회원가입을 파일 입출력으로 관리하는 클래스
BufferedReader 사용할 때 int형은 Integer.parseInt 쓰기
BufferedWriter 사용할 때 int형을 String형태로 읽어와야 하기 때문에 String.valueOf 사용하는 거 잊지 않기
 ----------------------------------------------------------------------------------------*/
public class LogManager {
    private String filename; //
    private ArrayList<Signup> udata; // 가변배열로 유저정보가 들어있는 Signup 클래스를 정의

    public LogManager(String file) {
        filename = file;
        udata = new ArrayList<>();
    }

    public void logmenu() {
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. 비밀번호 찾기");
        System.out.println("4. 프로그램 종료");
    }

    public void add(Signup signup) {
        udata.add(signup);
    }

    // 중복된 아이디 값이 있는지 확인
    public int checkData(String id, String username) {
        for (int i = 0; i < udata.size(); i++)
            if (udata.get(i).getId().equals(id) || udata.get(i).getUsername().equals(username)) return -1;
        return 1;
    }

    // 입력받은 id, password 값의 위치를 반환
    public int findLocation(String id, String password) {
        for(int i = 0; i < udata.size(); i++)
            if(udata.get(i).getId().equals(id) && udata.get(i).getPassword().equals(password)) return i;
        return -1;
    }

    // 비밀번호 찾기 메서드 기능은 위의 메서드와 동일
    public int findPassword(String id, String name) {
        for(int i = 0; i < udata.size(); i++)
            if(udata.get(i).getId().equals(id) && udata.get(i).getUsername().equals(name)) return i;
        return -1;
    }

    public void load() { // 파일 로드 -> 해당 파일 정보를 udata에 저장
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                String id = stringTokenizer.nextToken();
                String password = stringTokenizer.nextToken();
                String username = stringTokenizer.nextToken();
                add(new Signup(id, password, username));
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

    // udata에 담긴 정보들을 txt 파일에 저장..
    public void save() {
        try {
            FileWriter writer = new FileWriter(filename);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for(Signup signup : udata) {
                bufferedWriter.write(signup.getId() + ",");
                bufferedWriter.write(signup.getPassword() + ",");
                bufferedWriter.write(signup.getUsername());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(filename + " 을 저장하는 도중 오류가 발생하였습니다.");
        }
    }

    public String login() { // 로그인, 회원가입 메서드
        Scanner sc = new Scanner(System.in);
        load();
        Player player = new Player();
        for (int i = 0; i < 100; i++) {
            logmenu();
            int logmenu = sc.nextInt();
            // 로그인
            if (logmenu == 1) {

                System.out.print("로그인 할 아이디를 입력하세요 : ");
                String id = sc.next();
                System.out.print("로그인 할 비밀번호를 입력하세요 : ");
                String password = sc.next();
                int pos = findLocation(id, password);
                if(pos == -1) {
                    System.out.println("로그인에 실패했습니다. 다시 입력해주세요");
                    continue;
                } else {
                    System.out.println("로그인에 성공했습니다.");
                    System.out.println(udata.get(pos).getUsername() + " 님 환영합니다.");
                    return udata.get(pos).getUsername();
                }
                // 회원가입 -> 파일 쓰기
            } else if (logmenu == 2) {
                System.out.print("새로운 아이디를 입력하세요 : ");
                String id = sc.next();
                System.out.print("새로운 비밀번호를 입력하세요 : ");
                String password = sc.next();
                System.out.print("사용할 닉네임을 입력하세요 : ");
                String username = sc.next();
                int check = checkData(id, username); // 아이디 or 닉네임 중복 확인.
                if (check == -1) {
                    System.out.println("중복된 데이터가 있습니다. 다시 입력해주세요.");
                    continue;
                } else {
                    player = new Player();
                    // 유저의 데이터 저장
                    add(new Signup(id, password, username));
                    save();
                    int money = 0;
                    String rate = "0%";
                    String record = "0";
                    // 해당 플레이어의 이름/머니를 저장
                    player.add(new PlayerInfo(username, money, rate, record));
                    player.save(username);
                    System.out.println("회원가입에 성공하였습니다.");
                }
                // 비밀번호 찾기
            } else if(logmenu == 3) {
                System.out.print("찾을 아이디를 입력하세요 : ");
                String id = sc.next();
                System.out.print("찾을 아이디의 닉네임을 입력하세요 : ");
                String name = sc.next();
                int pos = findPassword(id, name);
                System.out.println("찾는 아이디의 비밀번호는 " + udata.get(pos).getPassword() + " 입니다.");
            } else System.exit(0);
        } // for
        return null;
    } // login method end
}