package ru.otus;

/**
 * To start the application:
 * ./gradlew build
 * java -jar ./hw01-gradle/build/libs/gradleHelloWorld-0.1.jar
 * <p>
 * To unzip the jar:
 * unzip -l hw01-gradle.jar
 * unzip -l gradleHelloWorld-0.1.jar
 */
public class Main {
    public static void main(String... args) throws Exception {
        Class<DemoClass> clazz = DemoClass.class;
        RunTests.runTests(clazz.getName());
    }

}
