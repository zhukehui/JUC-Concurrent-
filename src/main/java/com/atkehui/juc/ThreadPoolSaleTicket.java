package com.atkehui.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author eternity
 * @create 2019-10-14 23:51
 */
class Ticket{//资源类
    private int number = 30;
    private Lock lock = new ReentrantLock();
    public synchronized void sale()
    {
        lock.lock();//加锁
        try
        {
            if (number>0)
            {
                System.out.println(Thread.currentThread().getName()+"\t 卖出第："+(number--)+"\t 还剩下："+number);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            lock.unlock();//释放锁
        }
    }
}
/**
 * 题目：三个售票员     卖出      30张票
 * 目的：如何写出企业级的多线程程序
 *
 *  ** 高内聚，低耦合
 *
 *  1 高内低耦的前提下，线程       操作          资源类
 *
 */
public class ThreadPoolSaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ExecutorService executorService = Executors.newFixedThreadPool(3);//一池5线程
        //ExecutorService executorService = Executors.newSingleThreadExecutor();//一池1线程
        //ExecutorService executorService = Executors.newCachedThreadPool();//一池N线程 （可扩容的）
        for (int i = 0; i < 30; i++) {
            executorService.execute(() -> {
                    ticket.sale();
            });
        }
        executorService.shutdown();
    }
}
