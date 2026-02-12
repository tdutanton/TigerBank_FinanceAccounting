package Repository;

import Domain.Operation.Operation;
import Utils.Logging.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * String - id операции
 */
@Component
public class OperationRepository extends BaseRepository<Operation> {
    public OperationRepository(Logger logger) {
        super(logger);
    }
}
