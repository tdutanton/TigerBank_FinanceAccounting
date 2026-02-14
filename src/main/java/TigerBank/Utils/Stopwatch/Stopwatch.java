package TigerBank.Utils.Stopwatch;

public class Stopwatch {

  private long startNanos;
  private String operationName;

  public static Stopwatch start(String operationName) {
    Stopwatch sw = new Stopwatch();
    sw.operationName = operationName;
    sw.startNanos = System.nanoTime();
    return sw;
  }

  public void stop() {
    long duration = System.nanoTime() - startNanos;
    double ms = duration / 1_000_000.0;
    System.out.printf("[%s] выполнено за %.2f мс%n", operationName, ms);
  }
}
