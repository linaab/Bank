public class Main {
    public static void  main(String[] args){
        Distribution distribution = new Distribution(5);
        Thread t = new Thread(distribution);
        t.start();
    }
}
