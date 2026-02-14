package TigerBank.Presentation;

import TigerBank.Domain.Operation.Operation;
import java.util.ArrayList;

public class OperationsDisplay {

  private OperationsDisplay() {
  }

  public static void PrintOperations(ArrayList<Operation> operations) {
    if (operations.isEmpty()) {
      System.out.println("Операции отсутствуют");
      return;
    }
    for (Operation operation : operations) {
      System.out.println(operation);
    }
  }

}
