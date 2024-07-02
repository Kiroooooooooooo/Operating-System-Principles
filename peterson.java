package peterson;

public class peterson {
    public static void main(String[] args) {
        runcode r1 = new runcode(0);
        runcode r2 = new runcode(1);
        r1.start();
        r2.start();
    }
}
class order{
    public static int turn;
    public static boolean[] flag = {false,false};
}
class runcode extends Thread{
    public int id;
    public runcode(int id){this.id = id;}
    public void run(){
        order.flag[id] = true;
        order.turn = 1-id;
        while (order.turn == 1-id && order.flag[1-id]){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" +i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        order.flag[id]=false;
        order.turn = 1 - id;
    }
}
