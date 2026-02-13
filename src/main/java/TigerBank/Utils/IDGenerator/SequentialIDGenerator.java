package TigerBank.Utils.IDGenerator;

import org.springframework.stereotype.Component;

public class SequentialIDGenerator implements IDGenerator {

  private final String prefix;
  private long currentId = 1;

  public SequentialIDGenerator(String prefix) {
    if (prefix == null || prefix.trim().isEmpty()) {
      throw new IllegalArgumentException("Prefix cannot be null or empty");
    }
    this.prefix = prefix;
  }

  @Override
  public synchronized String nextId() {
    return prefix + currentId++;
  }
}

