package com.atkehui.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author eternity
 * @create 2019-10-14 20:37
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟3个停车位,(信号量)
        for (int i = 1; i <= 6; i++)
        {
            new Thread(() ->
            {
                boolean flag = false ;
                try {
                    semaphore.acquire();//获取，（信号量减一）
                    flag = true ;
                    System.out.println(Thread.currentThread().getName()+"\t 抢占到车位");
                //暂停n秒线程
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName()+"\t 离开车位*****");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (flag == true){
                        semaphore.release();//恢复
                    }
                }
            }, String.valueOf(i)).start();   
        }
    }

    /**
     * 在信号量上我们定义两种操作：
     * acquire（获取） 当一个线程调用 acquire 操作时， 它要么通过成功获取信号量（信号量减 1）， 要么一直等下
     * 去， 直到有线程释放信号量， 或超时。
     * release（释放） 实际上会将信号量的值加 1， 然后唤醒等待的线程。 信号量主要用于两个目的， 一个是用于多
     * 个共享资源的互斥使用， 另一个用于并发线程数的控制。
     */
}
