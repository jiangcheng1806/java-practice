package com.jiangcz.application.jdk.volatiles;

/**
 * 对volatile变量的读写具有原子性，但是其他操作并不一定具有原子性，一个简单的例子就是i++。由于该操作并不具有原子性，故而即使该变量被volatile修饰，多线程环境下也不能保证线程安全。
 */
public class TestVolatile {
    public static void main(String[] args) throws InterruptedException {

        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();
        threadDemo.flag = false;
        System.out.println("已将flag置为" + threadDemo.flag);

    }

    static class ThreadDemo implements Runnable {

        boolean flag = true; //没有使用volatile

        @Override
        public void run() {
            //在主线程将子线程实例的flag置为false后,子线程中的flag可能还是true。
            // 这就是多线程的内存可见性问题。对于一个没有volatile修饰的的共享变量,当一个线程对其进行了修改，另一线程并不一定能马上看见这个被修改后的值。为什么会出现这种情况呢？这就要从java的内存模型谈起。
            System.out.println("Flag=" + flag);

        }

    }
}
