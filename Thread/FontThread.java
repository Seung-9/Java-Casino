package Casino.Thread;

/*-----------------------------------
좀 더 게임과 같이 만들기 위해서
텍스트 문자를 Thread.sleep를 이용하여 출력
------------------------------------*/
public class FontThread implements Runnable{
    @Override
    public void run() {
            try{
                Thread.sleep(200);
                System.out.println("      __                _____________  _____________   _");
                Thread.sleep(200);
                System.out.println("     |  |              |_____   _____||_____   _____| | |");
                Thread.sleep(200);
                System.out.println(" ____|  |_____             /   /         /   /        | |__");
                Thread.sleep(200);
                System.out.println("|_____________|           /    \\        /    \\        |  __|");
                Thread.sleep(200);
                System.out.println("       _                /  / \\  \\      /  / \\  \\      | |");
                Thread.sleep(200);
                System.out.println("      / \\              /__/   \\__\\   /__/    \\__\\     |_|");
                Thread.sleep(200);
                System.out.println("      \\_/                              _____________");
                Thread.sleep(200);
                System.out.println(" _____| |______                       |__________  |");
                Thread.sleep(200);
                System.out.println("|______________|                                |  |");
                Thread.sleep(200);
                System.out.println("    ________                                    |  |");
                Thread.sleep(200);
                System.out.println("   |_____  |                                    |__|");
                Thread.sleep(200);
                System.out.println("    _____| |");
                Thread.sleep(200);
                System.out.println("   |   ____|");
                Thread.sleep(200);
                System.out.println("   |  |____ ");
                Thread.sleep(200);
                System.out.println("   |_______|\n");
            } catch(Exception e) {
                return;
            }
    }
}
