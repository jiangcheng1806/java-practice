package com.jiangcz.application.concurrent.lock;

import javafx.print.Printer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * 类名称：PrinterManager<br>
 * 类描述：信号量 Semaphore<br>
 * 创建时间：2018年08月01日<br>
 *
 * @author jiangcheng
 * @version 1.0.0
 */

public class PrinterManager {

    private final Semaphore semaphore;

    private final List<Printer> printers = new ArrayList<>();

    private PrinterManager(Collection<? extends Printer> printers){
        this.printers.addAll(printers);
        //这里重载方法，第二个参数为true，以公平竞争模式，防止线程饥饿
        this.semaphore = new Semaphore(this.printers.size(),true);
    }

    public Printer acquirePrinter() throws InterruptedException{
        semaphore.acquire();
        return getAvailablePrinter();
    }

    public void releasePrinter(Printer printer){
        putBackPrinter(printer);
        semaphore.release();
    }

    private synchronized Printer getAvailablePrinter(){
        Printer result = printers.get(0);
        printers.remove(0);
        return result;
    }

    private synchronized void putBackPrinter(Printer printer){
        printers.add(printer);
    }
}
