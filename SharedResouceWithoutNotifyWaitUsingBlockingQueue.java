import java.util.concurrent.*;
import java.lang.*;

class Main{
    public static void main(String args[]){

        SharedResource sr=new SharedResource();
        
        Thread producer = new Thread(()->{
            int value = 0;
            while (true){
            try {
                Thread.sleep(200);
                sr.produce(++value);
            } catch (Exception e) {
                
            } }
        });
        Thread consumer = new Thread(()->{
            while(true) {
            try {
               Thread.sleep(100);
                sr.consume();
            } catch (Exception e) {
                
            } }
        });

        producer.start();
        consumer.start();
    }
}

class SharedResource{

    private final int MAX_SIZE=5;

  // wait and notify not required as BlockingQueue will automatically blocks or unblocks the threads to queue, when it is empty or full
    private final BlockingQueue<Integer> bq=new LinkedBlockingQueue<>(MAX_SIZE);

    public  void produce(int value) throws InterruptedException   // no synchronization required as bq.put is already blocking nature
    {
        System.out.println("produced value "+value);
        bq.put(value);
    }
    public  void consume() throws InterruptedException // no synchronization required as bq.take is already blocking nature
    {
        int value = bq.take();
        System.out.println("consumed value "+value);
    }
    
}

