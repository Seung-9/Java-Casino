package Casino.Thread;

public class LoadingThread implements Runnable {
    @Override
    public void run() {
        System.out.println("결과를 내고있습니다.");
        String s ="███████████████████████████████████████████████████████████████████████\n";
        for (int i = 0; i < s.length(); i++) { // 로딩 효과 스레드
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print( s.charAt(i) );
        }
    }
}
