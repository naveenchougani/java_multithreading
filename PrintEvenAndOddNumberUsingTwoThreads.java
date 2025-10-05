import java.util.*;
import java.lang.*;

class PrintOddEven {

    private static int numbers = 0;
    boolean isOdd=true;

    public synchronized void printOdd() throws InterruptedException
    {
        if(!isOdd)
        {
            wait();
        }
        System.out.println(Thread.currentThread().getName()+" --> "+(++numbers));
        notifyAll();
        isOdd = !isOdd;
    }
    public synchronized void printEven() throws InterruptedException
    {
        if(isOdd)
        {
            wait();
        }
        System.out.println(Thread.currentThread().getName()+" --> "+(++numbers));
        notifyAll();
        isOdd = !isOdd;
    }
    
}
class Main{
    public static void main(String args[]) throws InterruptedException 
    {
    PrintOddEven p=new PrintOddEven();
    Thread odd=new Thread(()->{
        try {
            while(true){
                 p.printOdd();
            }
        } catch (Exception e) {
            
        }
   
    },"Odd Thread");
    Thread even=new Thread(()->{
        try {
            while(true){
             p.printEven();
            }
            } catch (Exception e) {
            
        }
       
    },"Even Thread");

    odd.start();
    even.start();
}
}
