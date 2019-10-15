package com.atkehui.juc;

import java.util.concurrent.*;

/**
 * @author eternity
 * @create 2019-10-14 22:41
 */

/**
 * 栈与队列：
 *  栈：先进后出（弹夹）
 *  队列：先进先出
 */

/**
 * BlockingQueue<E e>的核心方法：基本三个队列都有这些方法
 * add(E e)
 * 插入此队列的尾部，成功返回true，超出固定容量报错,java.lang.IllegalStateException: Queue full
 * remove()
 * 返回移除的元素，从该队列中删除指定元素的单个实例（如果存在）。 不存在报错java.util.NoSuchElementException
 *  offer(E e)
 * 如果可以在不超过队列的容量的情况下立即将其指定的元素插入到队列的尾部，如果队列已满，则返回 true和 false 。
 * poll()
 *  检索并删除此队列的头部，如果此队列为空，则返回 null 。
 *  put(E e)
 * 在该队列的尾部插入指定的元素，如果队列已满，则等待空间变为可用。
 *take()
 * 检索并删除此队列的头，如果队列为空，则等待空间变为可用。
 */
public class BlockingQueueDemo
{
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);//创建一个固定容量的阻塞队列 ，有界，会抛异常

        /*System.out.println(blockingQueue.add("A"));//插入此队列的尾部，成功返回true
        System.out.println(blockingQueue.add("B"));
        System.out.println(blockingQueue.add("C"));
       // System.out.println(blockingQueue.add("D"));//超出固定容量报错,java.lang.IllegalStateException: Queue full

        System.out.println(blockingQueue.remove());//返回移除的元素
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove());//超过容量报错java.util.NoSuchElementException
        */

        blockingQueue.put("a");//无返回值，将指定的元素添加到此队列，如果队列已满，则进入阻塞状态等待队列空闲
        blockingQueue.put("b");
        blockingQueue.put("c");
        //blockingQueue.put("d");

        System.out.println(blockingQueue.take());//返回移除元素，检索并删除此队列的头，
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());  // 如果队列为空，则等待空间变为可用。





/*        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);//有界队列，默认是Integer的最大值。（特殊值）

        System.out.println(blockingQueue.offer("A"));
        System.out.println(blockingQueue.offer("B"));
        System.out.println(blockingQueue.offer("C"));
        System.out.println(blockingQueue.offer("D"));
 offer(E e)
如果可以在不超过队列的容量的情况下立即将其指定的元素插入到队列的尾部，如果队列已满，则返回 true和 false 。
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
  poll()
 检索并删除此队列的头部，如果此队列为空，则返回 null 。
*/
//BlockingQueue blockingQueue = new SynchronousQueue();//无界队列 （生产一个消费一个）
    }
}
