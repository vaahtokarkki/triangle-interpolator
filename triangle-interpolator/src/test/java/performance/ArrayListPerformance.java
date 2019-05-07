/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import utils.MyArrayList;

/**
 *
 * @author lroni
 */
public class ArrayListPerformance {

    public static void main(String[] args) {

        /*
        30 runs of adding 1 million random integers to list. Performance is
        compared by avg of these runs.
         */
        ArrayList<Integer>[] javaListArr = new ArrayList[30];
        MyArrayList<Integer>[] myListArr = new MyArrayList[30];
        int javaSum = 0, mySum = 0;

        
        for (int i = 0; i < 30; i++) {
            javaListArr[i] = new ArrayList<>();
            myListArr[i] = new MyArrayList<>();
        }
        Random rand;
        for (int z = 0; z < 30; z++) {
            rand = new Random(1337 + z);

            long t1 = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) { //1milion
                javaListArr[z].add(rand.nextInt(10000000));
            }
            long t2 = System.currentTimeMillis();

            rand = new Random(1337 + z);

            long t3 = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) {
                myListArr[z].add(rand.nextInt(10000000));
            }
            long t4 = System.currentTimeMillis();

            System.out.println((t2 - t1) + " " + (t4 - t3));

            javaSum += (t2 - t1);
            mySum += (t4 - t3);

        }        
        double javaAvg = javaSum / 30.0;
        double myAvg = mySum / 30.0;

        System.out.println("Java avg " + javaAvg);
        System.out.println("My avg " + myAvg);
        
         
        //Delete 50 000 items from list, avg of 30 runs
        
        javaListArr = new ArrayList[30];
        myListArr = new MyArrayList[30];
        javaSum = 0;
        mySum = 0;

        for (int i = 0; i < 30; i++) {
            javaListArr[i] = new ArrayList<>();
            myListArr[i] = new MyArrayList<>();

            for (int z = 0; z < 50000; z++) {
                javaListArr[i].add(z);
                myListArr[i].add(z);
            }
        }

        for (int z = 0; z < 30; z++) {
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < javaListArr[z].size(); i++) {
                javaListArr[z].remove(i);
            }
            long t2 = System.currentTimeMillis();
            for (int i = 0; i < myListArr[z].size(); i++) {
                myListArr[z].remove(i);
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
        
        
        javaListArr = new ArrayList[30];
        myListArr = new MyArrayList[30];
        javaSum = 0;
        mySum = 0;

        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < 30000; i++) {
            items.add(i);
        }

        Collections.shuffle(items);

        for (int i = 0; i < 30; i++) {
            javaListArr[i] = new ArrayList<>();
            myListArr[i] = new MyArrayList<>();

            for (int z = 0; z < 30000; z++) {
                javaListArr[i].add(items.get(z));
                myListArr[i].add(items.get(z));
            }
            Collections.shuffle(items);
        }

        for (int z = 0; z < 30; z++) {
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < 30000; i++) {
                javaListArr[z].indexOf(i);
            }
            long t2 = System.currentTimeMillis();
            for (int i = 0; i < 30000; i++) {
                myListArr[z].indexOf(i);
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
