package 线程池;

import java.util.ArrayList;
import java.util.List;

public class ThreadPools {
    public static TaskPool tp = new TaskPool();
    public static void main(String [] args) throws Exception{
        Task t1 = new T1();
        Task t2 = new T2();
        Task t3 = new T1();
        Task t4 = new T2();
        ThreadPools.tp.addTask(t1);
        ThreadPools.tp.addTask(t2);
        ThreadPools.tp.addTask(t3);
        ThreadPools.tp.addTask(t4);
        TPOOL threadPool = new TPOOL(4);
        threadPool.start();
    }
}
interface Task{
    public void run();
}
class T1 implements Task{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class T2 implements Task{

    @Override
    public void run() {
        for (int i = 11; i < 20; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
class TaskPool{
    private List <Task> tds = new ArrayList<Task>();
    public synchronized void addTask(Task task){
        tds.add(task);
        this.notify();
    }
    public synchronized Task getTask() throws InterruptedException {
        if(tds.size()==0)this.wait();
        Task task = tds.remove(0);
        return task;
    }
}
class TPOOL{
    private List <Thread> tds =new ArrayList <Thread> ();
    public TPOOL(int size){
        for(int i = 0; i < size; i++){
            Thread td = new Runcode();
            tds.add(td);
        }
    }
    public void start(){
        for(Thread td : tds){
            td.start();
        }
    }
}
class Runcode extends Thread{
    public void run(){
        try{
            while(true){
                Task task = ThreadPools.tp.getTask();
                task.run();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}