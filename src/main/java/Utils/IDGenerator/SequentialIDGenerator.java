package Utils.IDGenerator;

import lombok.AllArgsConstructor;

public class SequentialIDGenerator implements IDGenerator {
    private long currentId;
    private final String prefix;

    public SequentialIDGenerator(String prefix) {
        this(1L, prefix);
    }

    private SequentialIDGenerator(long startId, String prefix) {
        if (prefix == null) throw new IllegalArgumentException("Prefix cannot be null");
        this.currentId = Math.max(1, startId);
        this.prefix = prefix;
    }

    @Override
    public synchronized String nextId() {
        return prefix + currentId++;
    }
}

