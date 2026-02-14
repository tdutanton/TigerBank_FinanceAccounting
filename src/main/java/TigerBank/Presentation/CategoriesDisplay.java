package TigerBank.Presentation;

import TigerBank.Domain.Category.Category;
import java.util.ArrayList;

public class CategoriesDisplay {

  private CategoriesDisplay() {
  }

  public static void PrintCategories(ArrayList<Category> categories) {
    if (categories.isEmpty()) {
      System.out.println("Счета отсутствуют");
      return;
    }
    for (Category category : categories) {
      System.out.println(category);
    }
  }

}
