package Utils.Logging;

public class ConsoleLogger implements Logger {
    @Override
    public void info(String message, String id) {
        System.out.printf(message);
    }
}