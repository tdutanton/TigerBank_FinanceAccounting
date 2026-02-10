package Utils;

public class SequentialIDGenerator implements IDGenerator {
    private int current = 1;

    @Override
    public synchronized Integer nextId() {
        return current++;
    }
}

