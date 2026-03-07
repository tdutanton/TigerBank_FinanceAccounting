package TigerBank.Domain.Category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import TigerBank.Domain.TxType.TxType;
import TigerBank.Factory.CategoryCreator.CategoryCreator;
import TigerBank.Factory.CategoryCreator.Impl.CategoryCreatorImpl;
import TigerBank.Factory.CategoryFactory.Impl.CategoryFactoryImpl;
import TigerBank.Utils.Logging.ConsoleLogger;
import org.junit.jupiter.api.Test;

class CategoryTest {

  @Test
  void shouldCreateCategoryWithValidData() {
    Category category = new IncomeCategory("cat_1", "Зарплата");

    assertEquals("cat_1", category.getId());
    assertEquals(TxType.INCOME, category.getType());
    assertEquals("Зарплата", category.getName());
  }

  @Test
  void shouldThrowExceptionWhenIdIsNull() {
    assertThrows(IllegalArgumentException.class, () ->
        new IncomeCategory(null, "Зарплата")
    );
  }

  @Test
  void shouldThrowExceptionWhenIdIsEmpty() {
    assertThrows(IllegalArgumentException.class, () ->
        new IncomeCategory("", "Зарплата")
    );
  }

  @Test
  void shouldThrowExceptionWhenNameIsNull() {
    assertThrows(IllegalArgumentException.class, () ->
        new IncomeCategory("cat_1", null)
    );
  }

  @Test
  void shouldThrowExceptionWhenNameIsEmpty() {
    assertThrows(IllegalArgumentException.class, () ->
        new IncomeCategory("cat_1", "   ")
    );
  }

  @Test
  void shouldHaveCorrectToString() {
    Category category = new ExpenseCategory("cat_1", "Продукты");
    assertTrue(category.toString().contains("id=cat_1"));
    assertTrue(category.toString().contains("name=Продукты"));
  }

  @Test
  void correctFabric() {
    CategoryCreator creator = new CategoryCreatorImpl(new CategoryFactoryImpl(),
        new ConsoleLogger());

    Category category = creator.createCategory("cat_1", TxType.EXPENSE, "Продукты");
    assertTrue(category.toString().contains("id=cat_1"));
    assertTrue(category.toString().contains("name=Продукты"));
  }
}