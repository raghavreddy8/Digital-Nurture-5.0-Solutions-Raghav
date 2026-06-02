class play extends Thread{
    @Override
    public void run(){
        for (int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"This code is running inside a thread.");
        }
    }
}

public class ThreadDemo{
    public static void main(String[] args){
        Thread p1=new play();
        Thread p2=new play();

        p1.start();      
        p2.start();
    }
}