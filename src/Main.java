public class Main {

    public static void main(String[] args) {

        Thread[] thread = new Thread[5];
        final int totalAmount = 10;
        Comprador buyer = new Comprador(totalAmount);
        Thread comprador = new Thread(buyer, "Buyer");

        comprador.start();

        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(new Alumnos(buyer), "Alumn"+i);
        }

        for (Thread value : thread) {
            value.start();
        }

    }

}
