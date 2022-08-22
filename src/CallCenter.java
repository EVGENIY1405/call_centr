import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

    public class CallCenter {

        private final int number_of_calls = 60; // число звонков
        private final int time_generation = 1000; //время для создания вызова
        private final int time_spend = 4000; //время, затраченное специалистом технической поддержки

        private LinkedBlockingQueue<Call> listCall = new LinkedBlockingQueue<>();

        public void addCall() {

            for (int i = 1; i <= number_of_calls; i++) {
                try {
                    listCall.add(new Call(i, "Client"));
                    Date data = new Date();
                    SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                    System.out.println("[" + formatForDate.format(data) + "]" + " принят " + i +
                            " вызов от аббонента / Общее количество не обработанных вызовов = " + listCall.size() +
                            " / " + Thread.currentThread().getName());
                    Thread.sleep(time_generation);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
            }

        }


        public void acceptCall() {

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(time_spend);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
                if (listCall.poll() != null) {
                    System.out.println(Thread.currentThread().getName()
                            + " принял вызов / Общее количество не обработанных вызовов = " + listCall.size());
                }
            }

        }

    }
