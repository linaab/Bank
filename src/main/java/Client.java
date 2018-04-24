import java.util.Random;

public class Client implements Comparable<Client>{
    private double serviceTime;
    private double money;
    private OperationType operationType;

    public double getServiceTime() {
        return serviceTime;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public double getMoney() {
        return money;
    }

    public Client() {
        Random random = new Random();
        serviceTime = 1000 + random.nextInt(5000);
        if (serviceTime % 2 == 0)
            operationType = OperationType.input;
        else operationType = OperationType.output;
        money = random.nextInt(500);
    }

    public int compareTo(Client o) {
        return 0;
    }
}
