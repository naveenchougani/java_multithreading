import java.lang.*;
import java.util.*;
import java.util.concurrent.*;

class Main {

  public static void main(String args[]) 
  {
    
  ExecutorService executor = Executors.newFixedThreadPool(3);

  for(int i=1;i<9;i++)
    {
        final int j=i;
        executor.execute ( () -> {
                                System.out.println("Task"+j+" executed by -> "+Thread.getCurrentThread().getName())
                             }
                      ) ;                                                   
    }
  }
}
