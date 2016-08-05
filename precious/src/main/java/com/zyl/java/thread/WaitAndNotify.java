package com.zyl.java.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * notify与wait只能使用在synchronized代码中，必须获取对象锁，才能运行
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class WaitAndNotify {
    
    public static void main(String[] args) {
        Game game = new Game();
        for ( int i=0; i<10; i++ ) {
            game.addShooter(new Shooter(i, game));
        }
        
        new Thread(game).start();
    }
}

class Shooter implements Runnable{
    private int dogNum;
    private Game game;
    
    public Shooter(int dogNum, Game game) {
        this.dogNum = dogNum;
        this.game = game;
    }
    
    @Override
    public void run() {
        game.prepare(this);
        System.out.println(dogNum + " fire in the hole!");
    }

    public int getDogNum() {
        return dogNum;
    }

    public Game getGame() {
        return game;
    }
}

class Game implements Runnable {
    private List<Shooter> shooterList = new ArrayList<Shooter>();
    
    @Override
    public void run() {
        System.out.println("Check every Shooter!");
        ready();
        try {
            Thread.sleep(1000);
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        go();
    }
    
    public void addShooter(Shooter shooter) {
        shooterList.add(shooter);
    }
    
    public synchronized void prepare(Shooter shooter) {
        System.out.println(shooter.getDogNum() + " ready!");
        try {
            wait();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
        System.out.println(shooter.getDogNum() + " go!");
        try {
            wait();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
    
    private void ready() {
        for ( Shooter shooter : shooterList ) {
            new Thread(shooter).start();
        }
    }
    
    private void go() {
        synchronized ( this ) {
            notifyAll();
        }
    }
}