import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Alumnos implements Runnable{

    private final Comprador comprador;
    int money;

    public Alumnos (Comprador comprador){
        this.comprador = comprador;
    }

    @Override
    public void run() {
        money = getMoney();
        System.out.printf("%s - The %s add to the amount %d euros.\n", DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()), Thread.currentThread().getName(), money);
        comprador.addMoney(money);

    }

    private int getMoney() {
        int euro = 0;

        try{
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10)+3);
            euro = ThreadLocalRandom.current().nextInt(5)+2;
        } catch (Exception e) {
            System.out.println("The alumn was interrupted while was sleeping.");
        }
        return euro;
    }


}
