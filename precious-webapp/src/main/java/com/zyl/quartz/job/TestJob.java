package com.zyl.quartz.job;


public class TestJob {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-quartz.xml");
    }
    
    public void execute() {
        try {
            System.out.println("Hello");
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }
}