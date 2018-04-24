import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;

import java.util.ArrayList;
import java.util.List;

public class Distribution extends Thread {
    Client c;
    //Operator p;
    List<Operator> operators;

    public Distribution(int oper_number) {
        operators = new ArrayList<Operator>(oper_number);

        for (int i = 1; i < oper_number; i++) {
            Operator operator=new Operator();
            operators.add(operator);
            new Thread(operator).start();
        }
    }

    @Override
    public void run() {
        super.run();
        try {
            while (true)//operators.get(0).cash.getBalance()>0)
                 {
                c = new Client();
                Operator operator = getMinCountInQueuePaymaster();
                operator.addClient(c);
                System.out.println("The client is in the queue " );
               // System.out.println("--------In the " + p.getNamePaymaster() + " queue " + p.queue.size() + " clients");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized Operator getMinCountInQueuePaymaster() {
        Operator operator = null;
        int minCount = operators.get(0).getCountInQueue();
        for (Operator p : operators)
            if (minCount > p.getCountInQueue()) {
                minCount = p.getCountInQueue();
                operator = p;
            }
        if (operator!=null)
            return operator;
        return operators.get(0);
    }
}

