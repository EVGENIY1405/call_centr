import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

    public class CallCenter {

        private static final int CALLS = 60; // число звонков
        private static final int GENERATE = 1000; //время для создания вызова
        private static final int SPEND = 4000; //время, затраченное специалистом технической поддержки

        private LinkedBlockingQueue<Call> listCall = new LinkedBlockingQueue<>();

        public void addCall() {

            for (int i = 1; i <= CALLS; i++) {
                try {
                    listCall.add(new Call(i, "Client"));
                    Date data = new Date();
                    SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                    System.out.println("[" + formatForDate.format(data) + "]" + " принят " + i +
                            " вызов от аббонента / Общее количество не обработанных вызовов = " + listCall.size() +
                            " / " + Thread.currentThread().getName());
                    Thread.sleep(GENERATE);
                } catch (InterruptedException exc) {
                    exc.printStackTrace();
                }
            }

        }


        public void acceptCall() {

            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(SPEND);
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
