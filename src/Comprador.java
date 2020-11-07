import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.CountDownLatch;

public class Comprador implements Runnable{

    final CountDownLatch countDownLatch;
    int money;
    int total=0;

    public Comprador(int money){
        this.money = money;
        countDownLatch = new CountDownLatch(money);
    }

    @Override
    public void run() {
        System.out.printf("%s - The %s is waiting the money.\n", DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()), Thread.currentThread().getName());
        try {
            countDownLatch.await();
            System.out.printf("%s - The %s is going to the shop to buy the gift\n", DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()), Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println("The buyer has been interrupted while he was waiting for the money.");
        }
    }

    public void addMoney(int money){
        total += money;
        System.out.printf("%s - The buyer has %d euros in total\n", DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()), total);
        for (int i = 0; i < money; i++) {
            countDownLatch.countDown();
        }
    }

}
