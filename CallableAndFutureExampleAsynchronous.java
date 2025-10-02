import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor=Executors.newFixedThreadPool(2);

        Callable<Integer> task = () -> {
            System.out.println("callable task started ");
            Thread.sleep(10000);
            System.out.println("Callable task completed");
            return 30;
        };

        System.out.println("submitting task now ");
        Future<Integer> future = executor.submit(task);
        System.out.println("Main thread is doing other work");
        Thread.sleep(1000);   // simulates main thread being busy and helps in see the logs of callable task submitted above is started simultaneously
        System.out.println("Main thread now calling future.get() which blocks main thread");
         int result=future.get();  // when ever you call this method, this blocks main thread to recived the result from future if its works is not yet finished
        System.out.println("Main thread is blocked unitll callable task completed and result is "+result);
       
        
    }
}
