/*
 * chsi
 * Created on 2018-11-27
 */
package com.zyl.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        Person p1 = new Person("Jack", "MALE", 20);
        Person p2 = new Person("Jhon", "FEMALE", 21);

        AtomicReference<Person> personAtomicReference = new AtomicReference<>(p1);
        Person p3 = personAtomicReference.get();
        if ( p3.equals(p1) ) {
            System.out.println("p3" + p3);
        } else {
            System.out.println("else" + p3);
        }
        System.out.println("get" + personAtomicReference.compareAndSet(null, p2) + personAtomicReference.get());
        System.out.println("get2" + personAtomicReference.compareAndSet(p1, p2) + personAtomicReference.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1-----------");
                Person people = personAtomicReference.get();
                people.setName("Tom1");
                people.setAge(people.getAge() + 1);
                System.out.println("thread1" + personAtomicReference.get());
                System.out.println("Thread1:" + personAtomicReference.get().toString());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2-----------");
                Person people = personAtomicReference.get();
                people.setName("Tom2");
                people.setAge(people.getAge() + 1);
                personAtomicReference.getAndSet(people);
                System.out.println("Thread2:" + personAtomicReference.get().toString());
            }
        }).start();
    }
}