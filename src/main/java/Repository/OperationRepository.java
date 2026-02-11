package Repository;

import Domain.Operation.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * String - id операции
 */
public class OperationRepository {
    private final Map<String, Operation> operations;

    public OperationRepository() {
        operations = new HashMap<>();
    }
}
