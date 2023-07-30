package ru.otus.exceptions;

public class MyException extends RuntimeException {

    public MyException() {
        System.out.println("====== taken MyException =====");
    }
}
