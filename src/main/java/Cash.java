public class Cash {
    private  double sum;
    private static Cash ourInstance = new Cash();

    public static Cash getInstance() {
        return ourInstance;
    }

    private Cash() {
        sum=1000;
    }
    public synchronized double getBalance() {
        return sum;
    }

    public synchronized void inpCash(double money) {
        sum += money;
    }
    public synchronized void outCash(double money) {
        sum -= money;
    }
}
