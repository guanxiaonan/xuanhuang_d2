package com.dmx;

public class Sellor  implements Runnable{

    private String name;
    private static int count = 100;
    private Object lock = new Object();

    public Sellor() {
    }

    public Sellor(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int temp;
        while (count > 0) {
            synchronized (lock) {
                temp = count;
                count --;
            }
            if (temp > 0) { // 假设是count,当A进入if,B 可能进行count -- 操作,但是输出的temp,所以不影响
                System.out.println(name + ":" + temp);
            }

        }
    }
//    public void run() {
//        while (count > 0) {
//            System.out.println(name + " + " + count);
//            synchronized (lock) {
//                count --;
//            }
//            System.out.println(name + " - " + count);
//
//            if (count > 0) {
//                System.out.println(name + ":" + count);
//            }
//
//        }
//    }
}
