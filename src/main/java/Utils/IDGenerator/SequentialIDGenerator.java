package Utils.IDGenerator;

public class SequentialIDGenerator implements IDGenerator {

  private final String prefix;
  private long currentId;

  public SequentialIDGenerator(String prefix) {
    this(1L, prefix);
  }

  private SequentialIDGenerator(long startId, String prefix) {
    if (prefix == null) {
      throw new IllegalArgumentException("Prefix cannot be null");
    }
    this.currentId = Math.max(1, startId);
    this.prefix = prefix;
  }

  @Override
  public synchronized String nextId() {
    return prefix + currentId++;
  }
}

