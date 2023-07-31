package ru.otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RunTests {
    public RunTests() {
    }

    public static void runTests(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        System.out.println("Object || " + clazz.getSimpleName() + " || has gotten");
        Method[] methods = clazz.getDeclaredMethods();
        Method beforeMethod = null;
        Method afterMethod = null;
        List<Method> testMethods = new ArrayList<>();
        // Search methods with annotations
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.toString().contains("Before")) {
                    beforeMethod = method;
                }
                if (annotation.toString().contains("Test")) {
                    testMethods.add(method);
                }
                if (annotation.toString().contains("After")) {
                    afterMethod = method;
                }
            }
        }
        // *** Run test methods step by step ***
        int countPassed = 0; // counter of methods without exceptions
        int countExceptions = 0; // counter of exceptions
        for (Method method : testMethods) {
            System.out.println("**************************");
            Object temp = clazz.getDeclaredConstructor().newInstance();
            try {
                beforeMethod.setAccessible(true);
                beforeMethod.invoke(temp);
                countPassed += 1;
            } catch (Exception e) {
                countExceptions += 1;
                System.out.println("------ " + e.toString() + " ------");
                break;
            }
            try {
                method.setAccessible(true);
                method.invoke(temp);
                countPassed += 1;
            } catch (Exception e) {
                countExceptions += 1;
                System.out.println("------ " + e.toString() + " ------");
            }
            try {
                afterMethod.setAccessible(true);
                afterMethod.invoke(temp);
                countPassed += 1;
            } catch (Exception e) {
                countExceptions += 1;
                System.out.println("------ " + e.toString() + " ------");
            } finally {
                // set instance link to null
                // for working of Garbage Collector
                temp = null;
            }
        }
        System.out.println("____________________________________________________");
        System.out.println("Total methods with exceptions - " + countExceptions);
        System.out.println("Passed methods - " + countPassed);
        System.out.println("* Total number of running methods - " + (countPassed + countExceptions) + " *");
    }
}
