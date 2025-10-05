import java.lang.*;
import java.util.*;

class SharedResource {

    Map<String, Integer> tpcm = new HashMap<>();
    public synchronized void accessSharedResource(String threadName) throws InterruptedException
    {
        tpcm.put(threadName, tpcm.getOrDefault(threadName,0)+1);
       Thread.sleep(200);
        System.out.println(threadName+" -> Releasing Lock and its count "+tpcm.get(threadName));
        
    }    
}

class Main{

    public static void main(String args[])  {

    SharedResource sr=new SharedResource();
    
    Thread hpt = new Thread(()->{
    while(true){
        try {
             sr.accessSharedResource("HighPriorityThread"); 
         Thread.sleep(200);
        } catch (Exception e) {
        }
      }              
    });
    Thread mpt = new Thread(()->{
      while(true) {
        try {
            
        sr.accessSharedResource("MediumPriorityThread"); 
                     Thread.sleep(200);
        } catch (Exception e) {  
        }     
   }
    });
        
    Thread lpt = new Thread(()->{
      while(true)
        {
        try {
             sr.accessSharedResource("LowPriorityThread"); 
                      Thread.sleep(200);
        } catch (Exception e) {
            
        }
     }     
    });

      // either any one of the below threads, gets executed most of the times, making other threads to be starved
    lpt.setPriority(Thread.NORM_PRIORITY);
    hpt.setPriority(Thread.MAX_PRIORITY);
    mpt.setPriority(Thread.MIN_PRIORITY);   
  

    hpt.start();
    lpt.start();
    mpt.start();
   }
}
