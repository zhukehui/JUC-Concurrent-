package com.atkehui.juc;

/**
 * @author eternity
 * @create 2019-10-14 19:24
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *  循环栅栏：
 *      可以手动设置一个屏障
 *      等到几个线程达到，才会执行。例如：集齐七颗龙珠才可以召唤神龙。
 *
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println(Thread.currentThread().getName() + "\t 集齐七颗龙珠，可以加冕为王"));
        for (int i = 1; i <= 7; i++)
        {
            int finalI = i;
            new Thread(() ->
            {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 当前集齐了第："+ finalI+"颗龙珠");
                    cyclicBarrier.await();//等待，（不见不散）
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
