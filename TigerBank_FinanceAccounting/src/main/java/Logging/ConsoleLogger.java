package Logging;

public class ConsoleLogger implements Logger {
    @Override
    public void info(String message) {
        System.out.println(message);
    }
}