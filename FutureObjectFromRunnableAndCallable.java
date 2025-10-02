import java.util.concurrent.*;
import java.time.*;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor=Executors.newFixedThreadPool(2);

        Callable<Integer> task = () -> {   // because callable is here picking up parameter which returns such type of value from its call method
            System.out.println("callable task completed which returns value ");
            return 30;
        };

        Runnable run=()->{   // but here runnable is of no parameter and its run method is of void type
            System.out.println("Runnable task completed which will not return any value");
        };
        Future<Object> future1 = executor.submit(run); // when you submit runnable to executor.submit() method
                                                        // by default it return Future<?> object instance, which contain null value;
      
        Future<Integer> future = executor.submit(task);  // But callable will alaways produces the future object which return the valueof its parameter type
        System.out.println(" value from future "+future.get()); // prints return value mentioned in the callable task
        System.out.println(" value of runner "+future1.get());  // prints null value
        
        
    }
}
