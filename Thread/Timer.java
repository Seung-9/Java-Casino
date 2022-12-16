package Casino.Thread;

public class Timer implements Runnable {
    private int n = 10;
    private boolean stop;
    public Timer() {
        this.stop = false;
    }

    @Override
    public void run() {
        while(!stop) {
                try {
                    n--;
                    Thread.sleep(1000);
                    time();
                    if(n == 0) {
                        System.out.println("제한시간이 완료되었습니다. 무작위로 선택됩니다.\n");
                        stop = true;
                        break;
                    }
                } catch (Exception e) {
                    return;
                }
        }
    }
    public void threadStop(boolean stop) {
        this.stop = stop;
    }

    public int time() {
        return n;
    }
}
