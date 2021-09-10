package com.ltsoft.game.infection.core.utils;

import javax.swing.text.html.Option;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class SingleTest {

    static Supplier<SingleTest> createAndGet = SingleTest::new;

    static Supplier<SingleTest> onlyGet = () -> SingleTest.instance;

    private static SingleTest instance;
    private static Supplier<SingleTest> currentSupplier = createAndGet;


    private SingleTest() {
        System.out.println("Creato Singleton");
    }

    public static SingleTest getInstance() {
        SingleTest currentInstance = currentSupplier.get();
        currentSupplier = onlyGet;
        return currentInstance;
    }

    public static void main(String[] args) {
        System.out.println("Try first creation of Singleton:");
        SingleTest instance = SingleTest.getInstance();
        System.out.println("Should be created...\n\n");

        System.out.println("Try second invocation of Singleton: ");
        SingleTest instance2 = SingleTest.getInstance();
        System.out.println("Should not be re-created, right ?");

    }
}
