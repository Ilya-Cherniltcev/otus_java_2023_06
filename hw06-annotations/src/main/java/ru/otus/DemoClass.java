package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import ru.otus.exceptions.MyException;

public class DemoClass {

    public DemoClass() {
    }

    @Before
    private void beforeMethod() {
        System.out.println("Run beforeMethod");
//        throw new MyException();
    }

    @Test
    private void test1Method() throws MyException {
        System.out.println("Run test1Method");
        throw new MyException();
    }

    @Test
    private void test2Method() throws MyException {
        System.out.println("Run test2Method");
        throw new MyException();
    }

    @Test
    private void test3Method() {
        System.out.println("Run test3Method");
    }

    @After
    private void afterMethod() {
        System.out.println("Run afterMethod");
        throw new MyException();
    }

    private void someMethodWithoutAnnotation() {
        System.out.println("Run someMethodWithoutAnnotation");
    }

    private void someMethodWithoutAnnotation2() {
        System.out.println("Run someMethodWithoutAnnotation2");
    }

    private void someMethodWithoutAnnotation3() {
        System.out.println("Run someMethodWithoutAnnotation3");
    }

}
