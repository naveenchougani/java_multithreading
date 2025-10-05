import java.lang.*;
import java.util.*;


class Main{
    public static void main(String args[]){
        SharedBuffer sb=new SharedBuffer();
        

        Thread producer = new Thread(()-> {
            int value = 0;
            try{
            while(true)
                {
                    sb.produce(value++);
                    Thread.sleep(1000);
                }
            } catch(Exception e){} 
        });
      
            Thread consumer = new Thread(()-> {
                try{
            while(true)
                {
                    sb.consume();
                    Thread.sleep(1000);
                }
                } catch(Exception e){} 
        });

        producer.start();
        consumer.start();       
    }
}


class SharedBuffer {
    private static final int MAX_SIZE=5;
    private static final Queue<Integer> buffer=new LinkedList<>();

    public synchronized void produce(int value) throws InterruptedException {
        while(buffer.size()==MAX_SIZE)
            {
                System.out.println("Buffer is full. Producer is going wait state");
                wait();
            }
        buffer.add(value);
        System.out.println("Proudced "+value+". Now notifyAll to just wake up any threads in wait state");
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while(buffer.isEmpty())
            {
                System.out.println("Buffer is empty. Consumer is going wait state");
                wait();
            }
        int consumedValue = buffer.poll();
         System.out.println("Consumed "+consumedValue+". Now notifyAll to just wake up any threads in wait state");
       
        notifyAll();
        return consumedValue;
    }
}

