package com.dmx;

public class App {
    public static void main(String[] args) {
        Sellor lisi = new Sellor("Lisi");
        Sellor zhangsan = new Sellor("Zhangsan");
        new Thread(lisi).start();
        new Thread(zhangsan).start();
    }
}
