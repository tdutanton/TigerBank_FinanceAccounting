package Repository;

import Domain.Operation.Operation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * String - id операции
 */
@Component
public class OperationRepository {
    private final Map<String, Operation> operations;

    public OperationRepository() {
        operations = new HashMap<>();
    }
}
