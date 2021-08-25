package com.ltsoft.game.infection.core.utils;

import com.ltsoft.game.infection.core.utils.math.Vector2f;

public class Test {

    public static void testNumbers() {
        double x1 = 0.001;
        double x2 = 0.002;
        double y1 = 0.05;
        double y2 = 0.003;

        long startMillis = System.currentTimeMillis();
        for(int i=0; i< 1_000_000; i++ ){
            x1 += x2;
            y1 += y2;
        }

        long elapsed = System.currentTimeMillis() - startMillis;

        System.out.println("Result: x1 = " + x1 + " - y1 = " + y1);
        System.out.println("Elapsed: " + elapsed + " millis");

    }


    public static void testVectors() {
        Vector2f v1 = new Vector2f(0.001f, 0.05f );
        Vector2f v2 = new Vector2f( 0.002f, 0.003f );

        long startMillis = System.currentTimeMillis();
        for(int i=0; i< 1_000_000; i++ ){
            v1 = v1.add( v2 );
        }

        long elapsed = System.currentTimeMillis() - startMillis;

        System.out.println("Result: v1 = " + v1);
        System.out.println("Elapsed: " + elapsed + " millis");
    }

    public static void main(String[] args) {

        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        for(int i=0; i<10; i++ ) {
            System.out.println("--- TEST: " + (i+1) + " ---");
            System.out.println("TESTING NUMBERS: ");
            testNumbers();
            System.out.println("TESTING VECTORS:");
            testVectors();
            System.out.println("---");
        }
    }
}
