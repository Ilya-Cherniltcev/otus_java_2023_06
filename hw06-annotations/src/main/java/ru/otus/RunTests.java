package ru.otus;

import java.io.Closeable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunTests {
    private static int countPassed = 0; // counter of methods without exceptions
    private static int countExceptions = 0; // counter of exceptions

    public RunTests() {
    }

    public static void runTests(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        System.out.println("Object || " + clazz.getSimpleName() + " || has gotten");
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> beforeMethod = getMethodsWithAnnotation("Before", methods);
        List<Method> testMethods = getMethodsWithAnnotation("Test", methods);
        List<Method> afterMethod = getMethodsWithAnnotation("After", methods);

        // *** Run test methods step by step ***
        for (Method method : testMethods) {
            System.out.println("**************************");
            Object temp = clazz.getDeclaredConstructor().newInstance();
            // run Before method
            int tempCounter = countExceptions;
            tryInvokeMethod(beforeMethod.get(0), temp);
            if (countExceptions > tempCounter) break;
            // run Test method
            tryInvokeMethod(method, temp);
            // run After method
            tryInvokeMethod(afterMethod.get(0), temp);
        }
        System.out.println("____________________________________________________");
        System.out.println("Total methods with exceptions - " + countExceptions);
        System.out.println("Passed methods - " + countPassed);
        System.out.println("* Total number of running methods - " + (countPassed + countExceptions) + " *");
    }

    private static List<Method> getMethodsWithAnnotation(String annotationText, Method[] methods) {
        // Search methods with annotations
        List<Method> methodsWithAnnotation = new ArrayList<>();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.toString().contains(annotationText)) {
                    methodsWithAnnotation.add(method);
                }
            }
        }
        return methodsWithAnnotation;
    }

    private static void tryInvokeMethod(Method method, Object o) {
        try {
            method.setAccessible(true);
            method.invoke(o);
            countPassed += 1;
        } catch (Exception e) {
            // check method name
            if (!Arrays.stream(method.getAnnotations()).findAny().get().toString().contains("After")) {
                countExceptions += 1;
            } else countPassed += 1;
            System.out.println("------ " + e.toString() + " ------");
        } finally {
            // set instance link to null
            // for working of Garbage Collector
            o = null;
        }
    }
}
