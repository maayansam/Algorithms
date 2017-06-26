package com.example.MyClock;

import com.google.common.base.Ticker;

import java.time.*;
import java.lang.*;
import java.util.concurrent.*;
import java.util.*;

/**
 * Created by maayan.s on 12/15/16.
 */
public class MyClock {

    private int hours;
    private int minutes;
    private int seconds;

    public MyClock ()
    {
        this(ZonedDateTime.now(ZoneId.systemDefault()));
    }

    public MyClock (ZonedDateTime local)
    {
        // default values for my clock with time of creation
        this(local.getHour(), local.getMinute(), local.getSecond());
    }

    public MyClock(int _hours, int _minutes, int _seconds)
    {
        //assuming values are correct
        hours = _hours; //24 hours format 1-24
        minutes = _minutes; //0-59
        seconds = _seconds; //0-59
    }

    public static void Sleep(long millis)
    {
        try{

            Thread.sleep(millis);
        }
        catch (InterruptedException e){
            System.out.println("sleep failure");
        }
    }


    public String TimeStr(String timeZone)
    {
        String str = String.format ("TimeZone: %s Time: %02d::%02d::%02d", timeZone, hours, minutes, seconds);

        return str;
    }
    public /*synchronized*/ void Display (int clockNum, String timeZone)
    {
        String timeStr = TimeStr(timeZone);

        int numOfTabs = clockNum;
        String tabs = "";
        while(numOfTabs > 1){
            tabs += "\t\t\t\t\t\t\t\t\t\t\t ";
            numOfTabs--;
        }

        System.out.print(String.format("\r%s Time Zone: %s  Time: %s", tabs, timeZone, timeStr));
        System.out.flush();

    }

    // increment the time by one seconds
    public void Inc()
    {
        //start basic and add multi-threading later
        seconds++;

        if(seconds == 60)
        {
            seconds = 0;
            minutes++;
            if(minutes == 60)
            {
                minutes = 0;
                hours++;
                if(hours == 24) //we are on next day
                {
                    hours = 0;
                }
            }
        }
    }

    public static void main(String[] args) {

        // initiates zones to present 3 different time zones
        ZoneId americaZone = ZoneId.of(ZoneId.SHORT_IDS.get("EST"));
        ZoneId parisZone = ZoneId.of(ZoneId.SHORT_IDS.get("ECT"));

        // create 3 clocks in parallel

        // this should do within a thread
        (new Thread(Ticker.getInstance())).start();

        (new Thread(new ClockRunnable(americaZone, "1"))).start();
        (new Thread(new ClockRunnable(parisZone, "2"))).start();
        (new Thread(new ClockRunnable("3"))).start();
        (new Thread(new ReadThread())).start();

        //System.exit(0);

    }

    public static class ClockRunnable implements Runnable {

        private ZoneId zoneId;
        private String clockNum;
        private Ticker ticker;

        public ClockRunnable (ZoneId zone, String clockNumber)
        {
            this(clockNumber);
            zoneId= zone;
        }

        public ClockRunnable (String clockNumber)
        {
            clockNum = clockNumber;
            zoneId = ZoneId.systemDefault();
            ticker = Ticker.getInstance();

        }
        public void run() {

            MyClock mClock = null;

            if(zoneId == ZoneId.systemDefault() ) {
                // create instance of my clock set to default time now
                mClock = new MyClock();
            }
            else{
                mClock = new MyClock(ZonedDateTime.now(zoneId));
            }

            for(int i=0; i<20; i++) {
                //update hash with proper time value
                Queue.addToHash(clockNum,mClock.TimeStr(zoneId.getId()));
                try {
                    synchronized (ticker.lock) {
                        ticker.lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mClock.Inc();


            }


        }

    }

    public static class Ticker implements Runnable {

        private volatile static Ticker ticker;
        private final long tickMs = 1000;


        public Object lock = new Object();
        public Object printLock = new Object();
        public CountDownLatch latch; //CyclicBarrier can be reseted more suitable here
        private Ticker () {

        }

        public static Ticker getInstance() {
            if (ticker == null) {
                synchronized (Ticker.class) {
                    if (ticker == null) {
                        ticker = new Ticker();
                    }
                }
            }

            return ticker;
        }

        @Override
        public void run() {
            while (true) {
                latch = new CountDownLatch(3);
                try {
                    Thread.sleep(tickMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock) {
                    lock.notifyAll();
                }

                try {
                    latch.await();
                    synchronized (printLock) {
                        printLock.notify();
                    }
                } catch (InterruptedException e) {

                }

            }
        }
    }

    public static class ReadThread implements Runnable {

        public void run(){


            for(int i=0; i<20; i++) {

                Iterator<String> ite = Queue.iterator();

                synchronized (Ticker.getInstance().printLock) {
                    try {
                        Ticker.getInstance().printLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                String toPrint = "";
                while (ite.hasNext()) {
                    String value = ite.next();
                    toPrint = String.format("%s\t\t%s", toPrint, value);
                }

                System.out.print(String.format("\r%s", toPrint));
            }

        }
    }

    public static class Queue {

        private static ConcurrentHashMap<String, String> myHashMap = new ConcurrentHashMap<String, String>(8, 0.9f, 1);

        public static void addToHash(String clockNum, String time) {
            myHashMap.put(clockNum, time);

        }

        public static Iterator<String> iterator()
        {
            return myHashMap.values().iterator();
        }

        public static void Clear(){
            myHashMap.clear();
        }

    }




}


