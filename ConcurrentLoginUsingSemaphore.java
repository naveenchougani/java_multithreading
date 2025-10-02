import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.concurrent.Semaphore;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Thread user1=new Thread(new LoginTask("user 1"));
        Thread user2=new Thread(new LoginTask("user 2"));
        Thread user3=new Thread(new LoginTask("user 3"));
        Thread user4=new Thread(new LoginTask("user 4"));
        user1.start();
        user2.start();
        user3.start();
        user4.start();
    }
}

class LoginTask implements Runnable {    // This instance to be passed to a Thread constructor

    private String user;
    private static final Semaphore sm=new Semaphore(3);  // the number reflects the no. of permitts on a shared resource

    public LoginTask(String user)
    {
        this.user=user;
    }

    private void login()
    {
        System.out.println(user+" logged in");
    }

    private void logout()
    {
        System.out.println(user+" logged out");
    }

    @Override
    public void run()
    {
        try {
            sm.acquire();
            login();
            Thread.sleep(2000);            
            
        } catch (InterruptedException  e) {
                        
        } finally {
            logout();
            sm.release();
        }
    }
    
    
}
