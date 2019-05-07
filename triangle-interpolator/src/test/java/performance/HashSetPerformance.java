/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import utils.MyHashSet;

/**
 *
 * @author lroni
 */
public class HashSetPerformance {

    public static void main(String[] args) {

        MyHashSet<Integer>[] mySet = new MyHashSet[30];
        HashSet<Integer>[] hashSet = new HashSet[30];
        int javaSum = 0, mySum = 0;
        double javaAvg, myAvg;

        /*
        for (int i = 0; i < 30; i++) {
            mySet[i] = new MyHashSet<>();
            hashSet[i] = new HashSet<>();
        }

        //add 100 000
        for (int z = 0; z < 30; z++) {

            long t1 = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                hashSet[z].add(i);
            }
            long t2 = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                mySet[z].add(i);
            }
            long t3 = System.currentTimeMillis();
            
            System.out.println((t2 - t1) + " " + (t3 - t2));

            javaSum += (t2 - t1);
            mySum += (t3 - t2);
        }
        
        javaAvg = javaSum / 30.0;
        myAvg = mySum / 30.0;

        System.out.println("Java avg " + javaAvg);
        System.out.println("My avg " + myAvg);
         */
        //remove()
        /*
        for (int i = 0; i < 30; i++) {
            mySet[i] = new MyHashSet<>();
            hashSet[i] = new HashSet<>();

            for (int z = 0; z < 500000; z++) {
                mySet[i].add(z);
                hashSet[i].add(z);
            }
        }

        for (int z = 0; z < 30; z++) {

            long t1 = System.currentTimeMillis();
            for (int i = 500000; i >= 0; i--) {
                hashSet[z].remove(i);
            }
            long t2 = System.currentTimeMillis();
            for (int i = 500000; i >= 0; i--) {
                mySet[z].remove(i);
            }
            long t3 = System.currentTimeMillis();

            System.out.println((t2 - t1) + " " + (t3 - t2));

            javaSum += (t2 - t1);
            mySum += (t3 - t2);
        }

        javaAvg = javaSum / 30.0;
        myAvg = mySum / 30.0;

        System.out.println("Java avg " + javaAvg);
        System.out.println("My avg " + myAvg);
         */
        
        //contains
        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < 500000; i++) {
            items.add(i);
        }

        Collections.shuffle(items);

        for (int i = 0; i < 30; i++) {
            mySet[i] = new MyHashSet<>();
            hashSet[i] = new HashSet<>();

            for (int z = 0; z < 500000; z++) {
                mySet[i].add(items.get(z));
                hashSet[i].add(items.get(z));
            }
            Collections.shuffle(items);
        }

        for (int z = 0; z < 30; z++) {

            long t1 = System.currentTimeMillis();
            for (int i = 0; i < 500000; i++) {
                hashSet[z].contains(i);
            }
            long t2 = System.currentTimeMillis();
            for (int i = 0; i < 500000; i++) {
                mySet[z].contains(i);
            }
            long t3 = System.currentTimeMillis();

            System.out.println((t2 - t1) + " " + (t3 - t2));

            javaSum += (t2 - t1);
            mySum += (t3 - t2);
        }

        javaAvg = javaSum / 30.0;
        myAvg = mySum / 30.0;

        System.out.println("Java avg " + javaAvg);
        System.out.println("My avg " + myAvg);
    }
}
