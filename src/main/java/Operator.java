import java.util.PriorityQueue;
import java.util.Queue;

public class Operator extends Thread {
    Cash cash;
    public Queue<Client> queue;


    public Operator() {
        queue = new PriorityQueue<Client>();
        cash = Cash.getInstance();
    }

    public int getCountInQueue() {
        return queue.size();
    }


    void addClient(Client c) {
        synchronized (queue) {
            queue.add(c);
            queue.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            while (queue.isEmpty())
                try {
                    synchronized (queue) {
                        queue.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            Client client = queue.poll();
            if (client.getOperationType().equals(OperationType.input)) {
                cash.inpCash(client.getMoney());
                System.out.println(" Клиент внес  " + client.getMoney());
            } else {
                if (client.getMoney() > cash.getBalance())
                    System.out.println("Недостаточно средств в кассе");
                else {
                    cash.outCash(client.getMoney());
                    System.out.println(" Клиент снял  " + client.getMoney());
                }
            }
            System.out.println("Баланс  " + cash.getBalance());
            try {
                Thread.sleep((long) client.getServiceTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
