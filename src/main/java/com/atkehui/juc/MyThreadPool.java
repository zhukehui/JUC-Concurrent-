package com.atkehui.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author eternity
 * @create 2019-10-15 0:15
 */
public class MyThreadPool
{
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2,//线程池中的常驻核心线程数
                5,//线程池中能够容纳同时执行的最大线程数（线程池最大数）
                2L,//多余的空闲线程的存活时间
                TimeUnit.SECONDS,//多余的空闲线程的存活时间的单位
                new ArrayBlockingQueue<>(3),//阻塞队列（有界）//(相当于银行的候客区)
                Executors.defaultThreadFactory(),//线程工厂，默认的
                /**
                 * 第七个参数 线程的拒绝策略：
                 *  1.AbortPolicy()//超过最大数加阻塞队列立刻中断并报错java.util.concurrent.RejectedExecutionException
                 *  2.DiscardOldestPolicy()//等待时间最长的抛弃
                 *  3.CallerRunsPolicy()//调用者回退，（谁分配的去找谁干）
                 *  4.DiscardPolicy()//不处理，直接丢弃
                 */
                //new ThreadPoolExecutor.AbortPolicy());//超过最大数加阻塞队列立刻中断并报错java.util.concurrent.RejectedExecutionException
                new ThreadPoolExecutor.CallerRunsPolicy());//拒绝策略

        try {
            for (int i = 0; i < 10; i++) {//模拟n个客户来银行办理业务，提交请求。customer
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务"+new Random().nextInt(10));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
