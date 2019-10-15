package com.atkehui.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author eternity
 * @create 2019-10-14 18:10
 */

/**
 * 示例：
 *  class Driver { // ...
 *    void main() throws InterruptedException {
 *      CountDownLatch startSignal = new CountDownLatch(1);
 *      CountDownLatch doneSignal = new CountDownLatch(N);
 *
 *      for (int i = 0; i < N; ++i) // create and start threads
 *        new Thread(new Worker(startSignal, doneSignal)).start();
 *
 *      doSomethingElse();            // don't let run yet
 *      startSignal.countDown();      // let all threads proceed
 *      doSomethingElse();
 *      doneSignal.await();           // wait for all to finish
 *    }
 *  }
 *
 *  CountDownLatch 主要有两个方法， 当一个或多个线程调用 await 方法时， 这些线程会阻塞。
 * 其它线程调用 countDown 方法会将计数器减 1(调用 countDown 方法的线程不会阻塞)， 当计数器的值变为 0 时，
 * 因 await 方法阻塞的线程会被唤醒， 继续执行
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);//计数器
        for (int i = 1; i <= 6; i++)
        {
            new Thread(() ->
            {
                /*try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                System.out.println(Thread.currentThread().getName()+"\t 离开---");
                countDownLatch.countDown();//相当于倒计时，每次减一
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();//等待（不见不散），等到上面线程执行完才执行
        //countDownLatch.await(3L,TimeUnit.SECONDS);//计时等待（过时不候），时间到了就执行
        System.out.println(Thread.currentThread().getName()+"\t 关闭***");
    }
}
/*
*
* 让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
* *
CountDownLatch 主要有两个方法， 当一个或多个线程调用 await 方法时， 这些线程会阻塞。
* 其它线程调用 countDown 方法会将计数器减 1(调用 countDown 方法的线程不会阻塞)，
* 当计数器的值变为 0 时， 因 await 方法阻塞的线程会被唤醒， 继续执行。
* *
解释： 6 个同学陆续离开教室后值班同学才可以关门。
* *
main 主线程必须要等前面 6 个线程完成全部工作后， 自己才能开干
*
*
* */