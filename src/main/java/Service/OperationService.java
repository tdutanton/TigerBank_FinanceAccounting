package Service;

import Domain.Operation.Operation;
import Domain.TxType.TxType;
import Repository.OperationRepository;
import Utils.Logging.Logger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OperationService {
    private final OperationRepository repository;
    private final Logger logger;

    public void createAndSaveOperation(String id,
                                       TxType type,
                                       String bankAccountId,
                                       long amount,
                                       LocalDateTime date,
                                       String categoryId,
                                       String description) {
        try {
            Operation operation = new Operation(id, type, bankAccountId, amount, date, categoryId, description);
            repository.add(operation);
        } catch (IllegalArgumentException e) {
            logger.info(e.toString());
        }
    }

    public void createAndSaveOperation(String id,
                                       TxType type,
                                       String bankAccountId,
                                       long amount,
                                       LocalDateTime date,
                                       String categoryId) {
        try {
            Operation operation = new Operation(id, type, bankAccountId, amount, date, categoryId);
            repository.add(operation);
        } catch (IllegalArgumentException e) {
            logger.info(e.toString());
        }
    }
}
